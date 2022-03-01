package com.example.omela.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.omela.account.CompletedOrderFragment
import com.example.omela.account.CurrentOrderFragment


class TabLayoutAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CurrentOrderFragment()
            }
            1 -> {
                CompletedOrderFragment()
            }
            else -> {
                Fragment()
            }
        }

    }
}