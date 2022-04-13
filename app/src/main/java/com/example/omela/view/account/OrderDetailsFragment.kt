package com.example.omela.view.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.view.adapters.BasketHistoryAdapter
import com.example.omela.databinding.FragmentOrderDetailsBinding

class OrderDetailsFragment : Fragment() {

    private var _binding: FragmentOrderDetailsBinding? = null
    private val binding
        get() = _binding!!

    private val basketHistoryAdapter = BasketHistoryAdapter()
//    private val basketHistoryList by lazy {
//        mutableListOf(
//            BasketItem(
//                "ПРИКОСНОВЕНИЕ",
//                R.drawable.flower_1,
//                8000,
//                15
//            ),
//            BasketItem(
//                "ИСКРЕННОСТЬ",
//                R.drawable.flower_2,
//                6800,
//            ),
//            BasketItem(
//                "ЧИСТОЕ СЕРДЦЕ",
//                R.drawable.flower_3,
//                4000,
//            )
//        )
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false)
        init()
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
        binding.orderAgain.setOnClickListener {
            val action =
                com.example.omela.view.account.OrderDetailsFragmentDirections.actionOrderDetailsFragmentToOrderFragment2()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            recyclerView.adapter = basketHistoryAdapter
        }
//        basketHistoryAdapter.setList(basketHistoryList)
    }

}