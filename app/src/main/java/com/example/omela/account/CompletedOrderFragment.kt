package com.example.omela.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.omela.R
import com.example.omela.adapters.OrderHistoryAdapter
import com.example.omela.databinding.FragmentCompletedOrderBinding
import com.example.omela.model.HistoryItem

class CompletedOrderFragment : Fragment() {

    private var _binding: FragmentCompletedOrderBinding? = null
    private val binding
        get() = _binding!!

    private val completedOrderAdapter = OrderHistoryAdapter()

    private val completedOrderList by lazy {
        mutableListOf(
            HistoryItem(
                "08.01.2022г",
                R.drawable.cat_minimal,
                R.drawable.flower_2,
                R.drawable.flower_3,
                15200
            ),
            HistoryItem(
                "25.12.2021г",
                R.drawable.floower_6,
                R.drawable.cat_minimal,
                R.drawable.flower_3,
                19600
            ),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_order, container, false)
        init()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.completedOrdersRecyclerView.adapter = completedOrderAdapter
        completedOrderAdapter.setList(completedOrderList)
    }
}