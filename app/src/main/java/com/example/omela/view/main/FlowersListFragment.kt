package com.example.omela.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.omela.view.Delegates
import com.example.omela.R
import com.example.omela.data.model.BasketItem
import com.example.omela.data.model.BouquetItem
import com.example.omela.view.adapters.CategoriesAdapter
import com.example.omela.view.adapters.FlowersAdapter
import com.example.omela.databinding.FragmentFlowersListBinding
import com.example.omela.data.model.CategoriesItem
import com.example.omela.data.model.FlowersItem
import com.example.omela.view.adapters.BouquetsAdapter
import com.example.omela.viewmodel.BouquetsViewModel
import com.example.omela.viewmodel.CategoriesViewModel
import com.example.omela.viewmodel.DatabaseViewModel
import com.example.omela.viewmodel.SaleBouquetsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FlowersListFragment : Fragment(), Delegates.BouquetClicked, Delegates.CategoryClicked,
    Delegates.ViewClicked {
    private var _binding: FragmentFlowersListBinding? = null
    private val binding
        get() = _binding!!

    private val catalogViewModel by viewModel<BouquetsViewModel>()
    private val catalogAdapter by lazy { BouquetsAdapter(requireContext(), this, this) }

    private val saleViewModel by viewModel<SaleBouquetsViewModel>()
    private val saleAdapter by lazy { BouquetsAdapter(requireContext(), this, this) }

    private val categoriesViewModel by viewModel<CategoriesViewModel>()
    private val categoriesAdapter = CategoriesAdapter(this)

    private val databaseViewModel by sharedViewModel<DatabaseViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_flowers_list, container, false)
//        viewLifecycleOwner.lifecycleScope.launch {
//            databaseViewModel.clear()
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            inflateMenu(R.menu.flowers_list_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_search -> {
                        Toast.makeText(requireContext(), "поиск", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_sort -> {
                        val action =
                            FlowersListFragmentDirections.actionFlowersListFragmentToFilterFragment()
                        findNavController().navigate(action)
                        true
                    }
                    else -> false
                }
            }
        }
        var databaseList: List<BasketItem> = listOf()
        init()
        lifecycle.addObserver(categoriesViewModel)
        categoriesViewModel.categoriesLiveData.observe(viewLifecycleOwner) {
            categoriesAdapter.setList(it.toList())
        }
        lifecycle.addObserver(databaseViewModel)
        databaseViewModel.productList.observe(viewLifecycleOwner) {
            databaseList = it
            Log.i("list", "observe $databaseList")
        }
        lifecycle.addObserver(catalogViewModel)
        catalogViewModel.bouquetsLiveData.observe(viewLifecycleOwner) {
            for (e in it) {
                for (i in databaseList) {
                    if (i.bouquetId == e.id) {
                        e.quantity = 1
                        Log.i("list", "quantity 1 ${e.name}")
                    } else {
                        e.quantity = 0
                        Log.i("list", "quantity 0 ${e.name}")
                    }
                }
                Log.i("list", "observe2 $databaseList")
            }
            Log.i("list", "observe3 $databaseList")
            catalogAdapter.setData(it.asList())
        }
        lifecycle.addObserver(saleViewModel)
        saleViewModel.saleBouquetsLiveData.observe(viewLifecycleOwner) {
            saleAdapter.setData(it.asList())
        }
        binding.callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$+996123456"))
            startActivity(intent)
        }
        binding.toAuthorButton.setOnClickListener {
            val action =
                FlowersListFragmentDirections.actionFlowersListFragmentToAllBouquetsFragment()
            findNavController().navigate(action)
        }
        binding.toSaleButton.setOnClickListener {
            val action = FlowersListFragmentDirections.actionFlowersListFragmentToSaleFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            recyclerViewAuthor.adapter = catalogAdapter
            recyclerViewSale.adapter = saleAdapter
            recyclerViewCategories.adapter = categoriesAdapter
        }
    }


    override fun onItemClick(category: CategoriesItem) {
        val action =
            FlowersListFragmentDirections.actionFlowersListFragmentToCategoryFragment(category.id, category.name)
        findNavController().navigate(action)
    }

    override fun onItemClick(bouquet: BouquetItem) {
        val action =
            FlowersListFragmentDirections.actionFlowersListFragmentToFlowerDetailsFragment(bouquet)
        findNavController().navigate(action)
    }

    override fun onItemClick(view: String, basketItem: BasketItem) {
        if (view == "plus") {
            Toast.makeText(requireContext(), "добавлено в корзину", Toast.LENGTH_SHORT).show()
            databaseViewModel.insert(basketItem)
//            databaseViewModel.productList.observe(viewLifecycleOwner) { it ->
//                    Log.i("list", "1 ${it.size}")
//            }
        } else {
            Toast.makeText(requireContext(), "minus", Toast.LENGTH_SHORT).show()
//            viewLifecycleOwner.lifecycleScope.launch {
                databaseViewModel.delete(basketItem)
   //         }
        }
    }
}