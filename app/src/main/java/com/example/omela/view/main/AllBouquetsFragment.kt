package com.example.omela.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.omela.R
import com.example.omela.data.model.BasketItem
import com.example.omela.data.model.BouquetItem
import com.example.omela.databinding.FragmentAllBouquetsBinding
import com.example.omela.view.Delegates
import com.example.omela.view.adapters.BouquetsAdapter
import com.example.omela.viewmodel.BouquetsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AllBouquetsFragment : Fragment(), Delegates.BouquetClicked, Delegates.ViewClicked {
    private var _binding: FragmentAllBouquetsBinding? = null
    private val binding get() = _binding!!
    private val catalogViewModel by viewModel<BouquetsViewModel>()
    private val catalogAdapter by lazy { BouquetsAdapter(requireContext(), this, this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_bouquets, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        lifecycle.addObserver(catalogViewModel)
        setupRecyclerViewCatalog()
        catalogViewModel.bouquetsLiveData.observe(viewLifecycleOwner){
            catalogAdapter.setData(it.asList())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun setupRecyclerViewCatalog(){
        binding.allBouquetsRecyclerView.apply {
//            adapter = catalogAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onItemClick(bouquet: BouquetItem) {
        val action = AllBouquetsFragmentDirections.actionAllBouquetsFragmentToFlowerDetailsFragment(bouquet)
        findNavController().navigate(action)
    }

    override fun onItemClick(view: String, basketItem: BasketItem) {
        if (view == "plus") {
            Toast.makeText(requireContext(), "добавлено в корзину", Toast.LENGTH_SHORT).show()
        }
    }

}