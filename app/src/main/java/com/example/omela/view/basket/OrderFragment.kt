package com.example.omela.view.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
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
        
        binding.confirmButton.setOnClickListener {
            val action = OrderFragmentDirections.actionOrderFragmentToStatusFragment()
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}