package com.example.omela.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.omela.view.Delegates
import com.example.omela.R
import com.example.omela.data.model.BasketItem
import com.example.omela.data.model.BouquetItem
import com.example.omela.databinding.FragmentCategoryBinding
import com.example.omela.view.adapters.BouquetsAdapter
import com.example.omela.viewmodel.CategoriesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment(), Delegates.BouquetClicked, Delegates.ViewClicked {

    private val args by navArgs<CategoryFragmentArgs>()
    private var _binding: FragmentCategoryBinding? = null
    private val binding
        get() = _binding!!

    private val categoriesViewModel by viewModel<CategoriesViewModel>()
    private lateinit var bouquetsAdapter: BouquetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        bouquetsAdapter = BouquetsAdapter(requireContext(), this, this)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            categoryName.text = args.name
        }
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        categoriesViewModel.getBouquetsByCategory(args.id)
        lifecycle.addObserver(categoriesViewModel)
        categoriesViewModel.categoryBouquets.observe(viewLifecycleOwner) {
            bouquetsAdapter.setData(it.bouquets.toList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            flowersInCategoryRv.layoutManager = GridLayoutManager(requireContext(), 2)
            flowersInCategoryRv.adapter = bouquetsAdapter
        }
    }

    override fun onItemClick(bouquet: BouquetItem) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToFlowerDetailsFragment(bouquet)
        findNavController().navigate(action)
    }

    override fun onItemClick(view: String, basketItem: BasketItem) {
        TODO("Not yet implemented")
    }
}