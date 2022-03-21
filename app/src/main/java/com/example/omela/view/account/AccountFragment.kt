package com.example.omela.view.account

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
import com.example.omela.R
import com.example.omela.databinding.FragmentAccountBinding
import com.example.omela.view.login.NeedToAuthorizeFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
        val user = Firebase.auth.currentUser
        if (user == null) {
            // User is not signed in
            parentFragmentManager.commit {
                replace<NeedToAuthorizeFragment>(R.id.nav_fragment)
                setReorderingAllowed(true)
                addToBackStack("name") // name can be null
            }
        }
        with(binding){
            branchesButton.setOnClickListener {
                val action =
                    com.example.omela.view.account.AccountFragmentDirections.actionAccountFragmentToBranchesFragment()
                findNavController().navigate(action)
            }
            editButton.setOnClickListener {
                val action =
                    com.example.omela.view.account.AccountFragmentDirections.actionAccountFragmentToEditAccountFragment()
                findNavController().navigate(action)
            }
            logOutButton.setOnClickListener {
                alertDialog()
            }
            historyButton.setOnClickListener {
                val action =
                    com.example.omela.view.account.AccountFragmentDirections.actionAccountFragmentToOrderHistoryFragment()
                findNavController().navigate(action)
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
        builder.setPositiveButton("да") { _, _ ->
            Firebase.auth.signOut()
            parentFragmentManager.commit {
                replace<NeedToAuthorizeFragment>(R.id.nav_fragment)
                setReorderingAllowed(true)
                addToBackStack("name") // name can be null
            }

        }
        builder.setNegativeButton("нет") { _, _ ->
            Toast.makeText(requireContext(),
                "действие отменено", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

}