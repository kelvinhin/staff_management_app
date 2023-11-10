package com.example.staffmanagementapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.staffmanagementapp.R
import com.example.staffmanagementapp.data.response.LoginResponse
import com.example.staffmanagementapp.databinding.FragmentLoginBinding
import com.example.staffmanagementapp.tools.isValidEmail
import com.example.staffmanagementapp.tools.isValidPassword
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class LoginFragment : Fragment() {
    private val searchViewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.vm = searchViewModel
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            searchViewModel.loginResponse.collect {
                Log.d("login", "Login success, token: ${it.token}")
                it.token?.let { token ->
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToStaffListFragment(token))
                }
            }
        }
        lifecycleScope.launch {
            searchViewModel.errorFlow.collect {
                Log.e("login", "Login failed, reason: $it")
                val errorMessage = try {
                    Json.decodeFromString<LoginResponse>(it).error
                } catch (e: Exception) {
                    it
                }

                AlertDialog.Builder(context)
                    .setTitle(R.string.login_error)
                    .setMessage(errorMessage)
                    .setPositiveButton(com.google.android.material.R.string.mtrl_picker_confirm) { dialog, which ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }
    }

    private fun setupView() {
        binding.edtEmail.addTextChangedListener {
            binding.txtLayoutEmail.error = ""
        }

        binding.edtPassword.addTextChangedListener {
            binding.txtLayoutPassword.error = ""
        }

        binding.btnLogin.setOnClickListener {
            val isEmailValid = binding.edtEmail.text.toString().isValidEmail()
            val isPasswordValid = binding.edtPassword.text.toString().isValidPassword()
            if (isEmailValid && isPasswordValid) {
                searchViewModel.doLogin(
                    email = binding.edtEmail.text.toString(),
                    password = binding.edtPassword.text.toString()
                )
            } else {
                if (!isEmailValid) {
                    binding.txtLayoutEmail.error = getString(R.string.email_error)
                }
                if (!isPasswordValid) {
                    binding.txtLayoutPassword.error = getString(R.string.password_error)
                }
            }
        }
    }

}