package com.example.omela.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import com.example.omela.Delegates
import com.example.omela.R
import com.example.omela.account.AccountFragmentDirections
import com.example.omela.adapters.BasketAdapter
import com.example.omela.databinding.FragmentBasketBinding
import com.example.omela.login.NeedToAuthorizeFragment
import com.example.omela.model.BasketItem
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class BasketFragment : Fragment(), Delegates.BasketClicked {
    private var _binding: FragmentBasketBinding? = null
    private val binding
        get() = _binding!!

    private val basketAdapter = BasketAdapter(this)
    private val basketList by lazy {
        mutableListOf(
            BasketItem(
                "ПРИКОСНОВЕНИЕ",
                R.drawable.flower_1,
                8000,
                15
            ),
            BasketItem(
                "ИСКРЕННОСТЬ",
                R.drawable.flower_2,
                6800,
            ),
            BasketItem(
                "ЧИСТОЕ СЕРДЦЕ",
                R.drawable.flower_3,
                4000,
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        init()
        binding.orderButton.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToOrderFragment()
            findNavController().navigate(action)
        }
        binding.toCurrentOrderButton.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToStatusFragment()
            findNavController().navigate(action)
        }
        binding.clearButton.setOnClickListener {
            alertDialog()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.apply {
            recyclerView.adapter = basketAdapter
        }
        basketAdapter.setList(basketList)
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
        builder.setTitle("вы действительно хотите очистить корзину?")
        builder.setPositiveButton("да") { _, _ ->
            Toast.makeText(requireContext(), "корзина очищена", Toast.LENGTH_SHORT).show()
//            parentFragmentManager.commit {
//                replace<Fav>(R.id.nav_fragment)
//                setReorderingAllowed(true)
//                addToBackStack("name") // name can be null
//            }
        }
        builder.setNegativeButton("нет") { _, _ ->
            Toast.makeText(requireContext(), "действие отменено", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    override fun onItemClick(basketItem: BasketItem) {
        alertDialogDelete()
    }

    private fun alertDialogDelete() {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
        builder.setTitle("вы действительно удалить этот элемент?")
        builder.setPositiveButton("да") { _, _ ->
            Toast.makeText(requireContext(), "корзина очищена", Toast.LENGTH_SHORT).show()
//            parentFragmentManager.commit {
//                replace<Fav>(R.id.nav_fragment)
//                setReorderingAllowed(true)
//                addToBackStack("name") // name can be null
//            }
        }
        builder.setNegativeButton("нет") { _, _ ->
            Toast.makeText(requireContext(), "действие отменено", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}