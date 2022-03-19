package com.example.omela.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.omela.R
import com.example.omela.account.EditAccountFragmentDirections
import com.example.omela.databinding.FragmentFlowerDetailsBinding

class FlowerDetailsFragment : Fragment() {

    private val args by navArgs<FlowerDetailsFragmentArgs>()
    private var _binding: FragmentFlowerDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flower_details, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            name.text = args.flower.flower_name
            image.setImageResource(args.flower.flower_image)
            if(args.flower.is_favorite) isFavorite.setImageResource(R.drawable.ic_heart_red)
            if(args.flower.status != null) {
                status.visibility = View.VISIBLE
                status.text = args.flower.status
            }
            price.text = "${args.flower.flower_price} c"
        }
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