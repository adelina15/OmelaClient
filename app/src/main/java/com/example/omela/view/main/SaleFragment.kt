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
import com.example.omela.databinding.FragmentSaleBinding
import com.example.omela.view.Delegates
import com.example.omela.view.adapters.BouquetsAdapter
import com.example.omela.viewmodel.BouquetsViewModel
import com.example.omela.viewmodel.SaleBouquetsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SaleFragment : Fragment(), Delegates.BouquetClicked, Delegates.ViewClicked {

    private var _binding: FragmentSaleBinding? = null
    private val binding
        get() = _binding!!

    private val saleViewModel by viewModel<SaleBouquetsViewModel>()
    private lateinit var bouquetsAdapter: BouquetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sale, container, false)
        bouquetsAdapter = BouquetsAdapter(requireContext(), this, this)
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
        lifecycle.addObserver(saleViewModel)
        init()
        saleViewModel.saleBouquetsLiveData.observe(viewLifecycleOwner){
            bouquetsAdapter.setData(it.asList())
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
        val action = SaleFragmentDirections.actionSaleFragmentToFlowerDetailsFragment(bouquet)
        findNavController().navigate(action)
    }

    override fun onItemClick(view: String, basketItem: BasketItem) {
        if (view == "plus") {
            Toast.makeText(requireContext(), "добавлено в корзину", Toast.LENGTH_SHORT).show()
        }
    }
}