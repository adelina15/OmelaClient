package com.example.omela.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.omela.FavoritesFragment
import com.example.omela.R
import com.example.omela.adapters.TabLayoutAdapter
import com.example.omela.databinding.FragmentOrderHistoryBinding
import com.google.android.material.tabs.TabLayoutMediator

class OrderHistoryFragment : Fragment() {
    private var _binding: FragmentOrderHistoryBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_history, container, false)

        val adapter = TabLayoutAdapter(this)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        //create text and fragments for each tab
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "текущие"
                }
                1 -> {
                    tab.text = "завершенные"
                }
            }
        }.attach()
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}