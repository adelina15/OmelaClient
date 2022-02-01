package com.example.omela.login

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.omela.MainActivity
import com.example.omela.R
import com.example.omela.SplashActivity
import com.example.omela.databinding.FragmentLoginBinding
import org.koin.android.ext.android.bind

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

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
        binding.openRegistration.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }
        binding.show.setOnClickListener {
            Toast.makeText(requireContext(), "${binding.show.tag}", Toast.LENGTH_SHORT).show()
        }
        binding.show.setOnClickListener { showHidePass() }

    }

    private fun showHidePass(){
        val button = binding.show
        val password = binding.registrationPassword
        if (button.tag == "R.id.ic_visible") {
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            button.setImageResource(R.drawable.ic_visibility)
            button.tag = "R.drawable.ic_visibility"
        } else {
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            button.setImageResource(R.drawable.ic_visible)
            button.tag = "R.id.ic_visible"
        }
    }

}
