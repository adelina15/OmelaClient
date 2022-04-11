package com.example.omela.view.login

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.databinding.FragmentRegistrationBinding
import com.example.omela.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding
    get() = _binding!!

    var number: String = ""
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }
        binding.verifyButton.setOnClickListener {
            login()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun login() {
        //получить номер телефона из editText
        number = binding.registrationPhone.text.trim().toString()
        if (binding.registrationPhone.length() == 13 && binding.registrationName.text.isNotEmpty()) {
            // sendVerificationCode(number)
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToCodeFragment2(number)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Введите номер телефона и имя", Toast.LENGTH_SHORT).show()
        }
    }

}