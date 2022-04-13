package com.example.omela.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.omela.R
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
        val flowers = args.bouquet.flowers
        with(binding){
            name.text = args.bouquet.name
            description.text = args.bouquet.description
            size.text = "диаметр ${args.bouquet.size.width}см \n" +
                        "ширина ${args.bouquet.size.height}см"
            for (f in flowers){
                content.text = "${f.name} ${f.quantity} шт \n"
            }

            Glide.with(requireContext()).load(args.bouquet.photo).into(image)
//            if(args.bouquet.is_favorite) isFavorite.setImageResource(R.drawable.ic_heart_red)
            if(args.bouquet.discount != 0) {
                status.visibility = View.VISIBLE
                status.text = "- ${args.bouquet.discount} %"
            }
            price.text = "${args.bouquet.price} c"
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