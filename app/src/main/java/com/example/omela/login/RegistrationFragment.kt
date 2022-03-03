package com.example.omela.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.databinding.FragmentRegistrationBinding
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
        change()
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
                findNavController().navigate(action)  }
        }
        binding.verifyButton.setOnClickListener {
            login()
        }
//        binding.show1.setOnClickListener { showHidePass(binding.show1, binding.registrationPassword) }
//        binding.show2.setOnClickListener { showHidePass(binding.show2, binding.registrationPasswordVerify) }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isFull(): Boolean {
        var isNumberCorrect = false
        var isNameCorrect = false
        binding.registrationPhone.addTextChangedListener {
            number = it?.trim().toString()
            if (number.length == 13) isNumberCorrect = true
        }
        binding.registrationName.addTextChangedListener {
            if (it?.isNotEmpty() == true) isNameCorrect  = true
        }
        if (isNumberCorrect && isNameCorrect) return true
        return false
    }

    private fun change(){
        if (isFull()) {
            binding.verifyButton.apply {
                background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.button_black
                )
                isEnabled = true
            }
        }
    }

    private fun login() {
        //получить номер телефона из editText
        number = binding.registrationPhone.text.trim().toString()
        if (isFull()) {
            // sendVerificationCode(number)
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToCodeFragment(number)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Введите номер телефона и имя", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showHidePass(view: ImageView, editText: EditText){
        if (view.tag == "R.id.ic_visible") {
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            view.setImageResource(R.drawable.ic_visibility)
            view.tag = "R.drawable.ic_visibility"
        } else {
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            view.setImageResource(R.drawable.ic_visible)
            view.tag = "R.id.ic_visible"
        }
    }
}