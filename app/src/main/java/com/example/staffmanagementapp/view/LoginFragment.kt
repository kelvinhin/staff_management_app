package com.example.staffmanagementapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.staffmanagementapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

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
                //TODO handle login response
                Log.d("login", "Login success, token: ${it.token}")
            }
        }
        lifecycleScope.launch {
            searchViewModel.errorFlow.collect {
                //TODO handle error message
                Log.e("login", "Login failed, reason: $it")
            }
        }
    }

    private fun setupView() {
        binding.btnLogin.setOnClickListener {
            //TODO check email and password before call login API
            searchViewModel.doLogin(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString()
            )
        }
    }

}