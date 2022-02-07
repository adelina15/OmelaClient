package com.example.omela

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.omela.databinding.FragmentFavoritesBinding

class AccountFragment : Fragment() {
//    private var _binding: FragmentFavoritesBinding? = null
//    private val binding
//        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
        // Inflate the layout for this fragment
//        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
//        return binding.root
    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

}