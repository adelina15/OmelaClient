package com.example.omela.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.account.AccountFragmentDirections
import com.example.omela.adapters.BasketAdapter
import com.example.omela.databinding.FragmentBasketBinding
import com.example.omela.model.BasketItem

class BasketFragment : Fragment() {
    private var _binding: FragmentBasketBinding? = null
    private val binding
        get() = _binding!!

    private val basketAdapter = BasketAdapter()
    private val basketList by lazy {
        mutableListOf(
            BasketItem(
                "ПРИКОСНОВЕНИЕ",
                R.drawable.flower_1,
                8000,
                15
            ),
            BasketItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                6800,
            ),
            BasketItem(
                "ЧИСТОЕ СЕРДЦЕ",
                R.drawable.flower_3,
                4000,
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        init()
        binding.orderButton.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToOrderFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            recyclerView.adapter = basketAdapter
        }
        basketAdapter.setList(basketList)
    }
}