package com.example.omela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.omela.R
import com.example.omela.data.model.FlowersItem
import com.example.omela.databinding.FragmentFavoritesBinding
import com.example.omela.databinding.FragmentNothingToShowBinding
import com.example.omela.view.adapters.FlowersAdapter

class NothingToShowFragment : Fragment(), Delegates.FlowerClicked {

    private var _binding: FragmentNothingToShowBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var flowersAdapter: FlowersAdapter

    private val flowersList by lazy {
        mutableListOf(
            FlowersItem(
                "НЕВЕСТЕ ДОРОГУ",
                R.drawable.flower_1,
                4900,
                false,
            ),
            FlowersItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                5000,
                false,
            )
        )
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nothing_to_show, container, false)
        flowersAdapter = FlowersAdapter(requireContext(), this)
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
//        val action = FavoritesFragmentDirections.actionFavoritesFragmentToFlowerDetailsFragment2(flower)
//        findNavController().navigate(action)
    }
}