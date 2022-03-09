package com.example.omela.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.omela.Delegates
import com.example.omela.R
import com.example.omela.adapters.FlowersAdapter
import com.example.omela.databinding.FragmentCategoryBinding
import com.example.omela.model.FlowersItem

class CategoryFragment : Fragment(), Delegates.FlowerClicked {

    private val args by navArgs<CategoryFragmentArgs>()
    private var _binding: FragmentCategoryBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var flowersAdapter: FlowersAdapter

    private val flowersList by lazy {
        mutableListOf(
            FlowersItem(
                "НЕВЕСТЕ ДОРОГУ",
                R.drawable.flower_1,
                4900,
                true,
            ),
            FlowersItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                5000,
                true,
            ),
            FlowersItem(
                "ВЛЮБЛЕННОСТЬ",
                R.drawable.flower_3,
                7100,
                false,
            ),
            FlowersItem(
                "ЭЛЕГАНТНОСТЬ",
                R.drawable.flower_4,
                3300,
                true,
            ),
            FlowersItem(
                "ВРЕМЯ ЛЮБИТЬ",
                R.drawable.flower_5,
                8900,
                true,
            ),
            FlowersItem(
                "ЛЮБОВЬ",
                R.drawable.floower_6,
                5700,
                false,
            ),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        flowersAdapter = FlowersAdapter(requireContext(), this)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            categoryName.text = args.categoryName
        }
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                val action = CategoryFragmentDirections.actionCategorieFragmentToFlowersListFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            flowersInCategoryRv.layoutManager = GridLayoutManager(requireContext(), 2)
            flowersInCategoryRv.adapter = flowersAdapter
        }
        flowersAdapter.setList(flowersList)
    }

    override fun onItemClick(flower: FlowersItem) {
        val action = CategoryFragmentDirections.actionCategorieFragmentToFlowerDetailsFragment(flower)
        findNavController().navigate(action)
    }
}