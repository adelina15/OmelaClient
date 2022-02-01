package com.example.omela.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.R
import com.example.omela.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding
    get() = _binding!!

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
                val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
                findNavController().navigate(action)  }
        }
        binding.verifyButton.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToCodeFragment()
            findNavController().navigate(action)
        }
        binding.show1.setOnClickListener { showHidePass(binding.show1, binding.registrationPassword) }
        binding.show2.setOnClickListener { showHidePass(binding.show2, binding.registrationPasswordVerify) }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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