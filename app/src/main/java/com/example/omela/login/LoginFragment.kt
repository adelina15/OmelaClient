package com.example.omela.login

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.databinding.FragmentLoginBinding
import com.example.omela.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    var number: String = ""
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberChangeListener()
        auth = FirebaseAuth.getInstance()
        with(binding.toolbar) {
            inflateMenu(R.menu.registration_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_skip -> {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }
        with(binding) {
//            openRegistration.setOnClickListener {
//                val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
//                findNavController().navigate(action)
//            }
//            show.setOnClickListener {
//                Toast.makeText(requireContext(), "${binding.show.tag}", Toast.LENGTH_SHORT).show()
//            }
//            show.setOnClickListener { showHidePass() }

            verifyButton.setOnClickListener {
                login()
            }

//            if(!editTextPhone.text.isNullOrEmpty() && !registrationPassword.text.isNullOrEmpty()){
//                verifyButton
//            }
        }


    }

    private fun numberChangeListener() {
        binding.editTextPhone.addTextChangedListener {
            if (it?.length == 13) {
                binding.verifyButton.apply {
                    background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.button_black
                    )
                    isEnabled = true
                }
            }
        }
    }

    private fun login() {
        //получить номер телефона из editText
        number = binding.editTextPhone.text.trim().toString()
        if (number.isNotEmpty() && number.length == 13) {
            // sendVerificationCode(number)
            val action = LoginFragmentDirections.actionLoginFragmentToCodeFragment(number)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Введите номер телефона", Toast.LENGTH_SHORT).show()
        }
    }

}
