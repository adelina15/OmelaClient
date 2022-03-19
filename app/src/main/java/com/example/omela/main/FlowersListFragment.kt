package com.example.omela.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.omela.Delegates
import com.example.omela.R
import com.example.omela.adapters.CategoriesAdapter
import com.example.omela.adapters.FlowersAdapter
import com.example.omela.databinding.FragmentFlowersListBinding
import com.example.omela.model.CategoriesItem
import com.example.omela.model.FlowersItem

class FlowersListFragment : Fragment(), Delegates.FlowerClicked, Delegates.CategoryClicked {
    private var _binding: FragmentFlowersListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var flowersAdapter: FlowersAdapter
    private val categoriesAdapter = CategoriesAdapter(this)
    private lateinit var saleAdapter: FlowersAdapter

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

    private val saleList by lazy {
        mutableListOf(
            FlowersItem(
                "НЕВЕСТЕ ДОРОГУ",
                R.drawable.flower_1,
                4900,
                false,
                "-15%",
            ),
            FlowersItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                5000,
                false,
                "-9%"
            ),
            FlowersItem(
                "ВЛЮБЛЕННОСТЬ",
                R.drawable.flower_3,
                7100,
                false,
                "-20%",
            ),
            FlowersItem(
                "ЭЛЕГАНТНОСТЬ",
                R.drawable.flower_4,
                3300,
                false,
                "-8%"
            ),
            FlowersItem(
                "ВРЕМЯ ЛЮБИТЬ",
                R.drawable.flower_5,
                8900,
                false,
                "-5%",
            ),
            FlowersItem(
                "ЛЮБОВЬ",
                R.drawable.floower_6,
                5700,
                false,
                "-10%",
            ),
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flowers_list, container, false)
        flowersAdapter = FlowersAdapter(requireContext(), this)
        saleAdapter = FlowersAdapter(requireContext(), this)
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
        binding.callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$+996123456"))
            startActivity(intent)
        }
        binding.toTopsButton.setOnClickListener {
            val action = FlowersListFragmentDirections.actionFlowersListFragmentToCategoryFragment("хиты продаж")
            findNavController().navigate(action)
        }
        binding.toAuthorButton.setOnClickListener {
            val action = FlowersListFragmentDirections.actionFlowersListFragmentToCategoryFragment("все букеты")
            findNavController().navigate(action)
        }
        binding.toSaleButton.setOnClickListener {
            val action = FlowersListFragmentDirections.actionFlowersListFragmentToCategoryFragment("распродажа")
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            recyclerViewTops.adapter = flowersAdapter
            recyclerViewAuthor.adapter = flowersAdapter
            recyclerViewSale.adapter = saleAdapter
            recyclerViewCategories.adapter = categoriesAdapter
        }
        flowersAdapter.setList(flowersList)
        categoriesAdapter.setList(categoriesList)
        saleAdapter.setList(saleList)
    }

    override fun onItemClick(flower: FlowersItem) {
        val action =
            FlowersListFragmentDirections.actionFlowersListFragmentToFlowerDetailsFragment(flower)
        findNavController().navigate(action)
    }

    override fun onItemClick(category: CategoriesItem) {
        val action =
            FlowersListFragmentDirections.actionFlowersListFragmentToCategoryFragment(category.category_name)
        findNavController().navigate(action)
    }
}