package com.example.omela

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.omela.adapters.CategoriesAdapter
import com.example.omela.adapters.FlowersAdapter
import com.example.omela.adapters.SaleAdapter
import com.example.omela.databinding.FragmentFlowersListBinding
import com.example.omela.model.CategoriesItem
import com.example.omela.model.FlowersItem
import com.example.omela.model.SaleItem

class FlowersListFragment : Fragment() {
    private var _binding: FragmentFlowersListBinding? = null
    private val binding
        get() = _binding!!

    private val flowersAdapter = FlowersAdapter()
    private val categoriesAdapter = CategoriesAdapter()
    private val saleAdapter = SaleAdapter()

    private val categoriesList by lazy {
        mutableListOf(
            CategoriesItem(
                "минимализм",
                R.drawable.cat_minimal
            ),
            CategoriesItem(
                "шебби-шик",
                R.drawable.cat_shik
            ),
            CategoriesItem(
                "рустик",
                R.drawable.cat_3
            ),
            CategoriesItem(
                "лофт",
                R.drawable.cat_4
            ),
            CategoriesItem(
                "бохо",
                R.drawable.cat_shik
            ),
        )
    }

    private val flowersList by lazy {
        mutableListOf(
            FlowersItem(
                "НЕВЕСТЕ ДОРОГУ",
                R.drawable.flower_1,
                4900,
                true
            ),
            FlowersItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                5000,
                true
            ),
            FlowersItem(
                "ВЛЮБЛЕННОСТЬ",
                R.drawable.flower_3,
                7100,
            ),
            FlowersItem(
                "ЭЛЕГАНТНОСТЬ",
                R.drawable.flower_4,
                3300,
                true
            ),
            FlowersItem(
                "ВРЕМЯ ЛЮБИТЬ",
                R.drawable.flower_5,
                8900,
                true
            ),
            FlowersItem(
                "ЛЮБОВЬ",
                R.drawable.floower_6,
                5700,
            ),
        )
    }

    private val saleList by lazy {
        mutableListOf(
            SaleItem(
                "НЕВЕСТЕ ДОРОГУ",
                R.drawable.flower_1,
                4900,
                "-15%",
                true
            ),
            SaleItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                5000,
                "-9%",
                true
            ),
            SaleItem(
                "ВЛЮБЛЕННОСТЬ",
                R.drawable.flower_3,
                7100,
                "-20%",
            ),
            SaleItem(
                "ЭЛЕГАНТНОСТЬ",
                R.drawable.flower_4,
                3300,
                "-8%",
                true
            ),
            SaleItem(
                "ВРЕМЯ ЛЮБИТЬ",
                R.drawable.flower_5,
                8900,
                "-5%",
                true
            ),
            SaleItem(
                "ЛЮБОВЬ",
                R.drawable.floower_6,
                5700,
                "-10%",
            ),
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_flowers_list, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            inflateMenu(R.menu.flowers_list_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_search -> {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
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
        binding.toTopsButton.setOnClickListener {
            val action = FlowersListFragmentDirections.actionFlowersListFragmentToFilterFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            recyclerViewTops.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerViewTops.adapter = flowersAdapter

            recyclerViewAuthor.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerViewAuthor.adapter = flowersAdapter

            recyclerViewSale.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerViewSale.adapter = saleAdapter

            recyclerViewCategories.adapter = categoriesAdapter
        }
        flowersAdapter.setList(flowersList)
        categoriesAdapter.setList(categoriesList)
        saleAdapter.setList(saleList)
    }
}