package com.example.omela

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.omela.adapters.FlowersAdapter
import com.example.omela.databinding.FragmentFavoritesBinding
import com.example.omela.model.FlowersItem

class FavoritesFragment : Fragment(), Delegates.FlowerClicked {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding
        get() = _binding!!

    private val flowersAdapter = FlowersAdapter(this)

    private val flowersList by lazy {
        mutableListOf(
            FlowersItem(
                "НЕВЕСТЕ ДОРОГУ",
                R.drawable.flower_1,
                4900,
                true,
                3
            ),
            FlowersItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                5000,
                true,
                2
            ))
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        init()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            topsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            topsRecyclerView.adapter = flowersAdapter
        }
        flowersAdapter.setList(flowersList)
    }

    override fun onItemClick(flower: FlowersItem) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToFlowerDetailsFragment(flower)
        findNavController().navigate(action)
    }
}