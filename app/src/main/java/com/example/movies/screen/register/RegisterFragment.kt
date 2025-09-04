package com.example.movies.screen.register

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.databinding.FragmentRegisterBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()


        binding.EditTextEmail.addTextChangedListener {
            binding.textInputMail.error = null

        }

        binding.EditTextPass.addTextChangedListener {
            binding.textInputPass.error = null

        }


        binding.buttonSignUp.setOnClickListener {
            if(validateInputs()){
                register()
            }
        }

        binding.textViewSignIn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginPasswordFragment())
        }

        binding.imageViewBack2.setOnClickListener {
            findNavController().popBackStack()
        }
    }



    private fun register(){
        val email = binding.EditTextEmail.text.toString().trim()
        val pass = binding.EditTextPass.text.toString().trim()
        viewModel.registerUser(email,pass)
    }

    private fun observeData(){
        viewModel.isError.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }


        viewModel.isSuccess.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginPasswordFragment())
                showCongratsDialog()
            }
        }
    }

    private fun shakeView(view: View) {
        val shake = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
        view.startAnimation(shake)
    }

    private fun validateInputs(): Boolean {
        var isValid = true



        val email = binding.EditTextEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.textInputMail.error = "Email is required"
            shakeView(binding.textInputMail)
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.textInputMail.error = "Invalid email format"
            shakeView(binding.textInputMail)
            isValid = false
        } else {
            binding.textInputMail.error = null
        }

        val pass = binding.EditTextPass.text.toString().trim()
        if (pass.isEmpty()) {
            binding.textInputPass.error = "Password is required"
            shakeView(binding.textInputPass)
            isValid = false
        } else if (pass.length < 6) {
            binding.textInputPass.error = "Password needs to be at least 6 characters"
            shakeView(binding.textInputPass)
            isValid = false
        } else {
            binding.textInputPass.error = null
        }

        return isValid
    }


    fun showCongratsDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_alert, null)

        val dialog = AlertDialog.Builder(requireContext(), R.style.TransparentDialog)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialogView.findViewById<MaterialButton>(R.id.buttonOK).setOnClickListener {
            dialog.dismiss()
        }



        dialog.show()
    }
}