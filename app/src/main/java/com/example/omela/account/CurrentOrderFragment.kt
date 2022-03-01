package com.example.omela.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.omela.R
import com.example.omela.adapters.OrderHistoryAdapter
import com.example.omela.databinding.FragmentCurrentOrderBinding
import com.example.omela.databinding.FragmentFlowersListBinding
import com.example.omela.model.CategoriesItem
import com.example.omela.model.HistoryItem

class CurrentOrderFragment : Fragment() {

    private var _binding: FragmentCurrentOrderBinding? = null
    private val binding
        get() = _binding!!

    private val currentOrderAdapter = OrderHistoryAdapter()

    private val currentOrderList by lazy {
        mutableListOf(
            HistoryItem(
                "в пути",
                R.drawable.cat_minimal,
                R.drawable.flower_2,
                null,
                19600
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_current_order, container, false)
        init()
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun init() {
        binding.currentOrdersRecyclerView.adapter = currentOrderAdapter
        currentOrderAdapter.setList(currentOrderList)
    }

}