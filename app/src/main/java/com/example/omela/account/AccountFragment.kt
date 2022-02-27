package com.example.omela.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.databinding.FragmentAccountBinding
import com.example.omela.main.FilterFragmentDirections

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            branchesButton.setOnClickListener {
                val action = AccountFragmentDirections.actionAccountFragmentToBranchesFragment()
                findNavController().navigate(action)
            }
            editButton.setOnClickListener {
                val action = AccountFragmentDirections.actionAccountFragmentToEditAccountFragment()
                findNavController().navigate(action)
            }
            logOutButton.setOnClickListener {
                alertDialog()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
        builder.setTitle("вы действительно хотите выйти?")
        builder.setMessage("You")
        builder.setPositiveButton("да") { _, _ ->
            //
        }
        builder.setNegativeButton("нет") { _, _ ->
            Toast.makeText(requireContext(),
                "действие отменено", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

}