package com.example.omela.login

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            inflateMenu(R.menu.registration_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_skip -> {
                        Toast.makeText(requireContext(), "hey", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
                findNavController().navigate(action)  }
        }
        binding.verifyButton.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToCodeFragment()
            findNavController().navigate(action)
        }

    }

}