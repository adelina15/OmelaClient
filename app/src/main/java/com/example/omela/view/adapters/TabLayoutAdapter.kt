package com.example.omela.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.omela.view.account.CompletedOrderFragment
import com.example.omela.view.account.CurrentOrderFragment


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