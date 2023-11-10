package com.example.staffmanagementapp.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.staffmanagementapp.data.request.LoginRequest
import com.example.staffmanagementapp.data.response.LoginResponse
import com.example.staffmanagementapp.repository.StaffManagementRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel() {
    private val _loginResponse: MutableSharedFlow<LoginResponse> by lazy {
        MutableSharedFlow()
    }

    val loginResponse by lazy {
        _loginResponse.asSharedFlow()
    }

    private val _mainLoading: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }
    val mainLoading: StateFlow<Boolean> by lazy {
        _mainLoading.asStateFlow()
    }

    private val _errorFlow: MutableSharedFlow<String> by lazy {
        MutableSharedFlow()
    }
    val errorFlow by lazy {
        _errorFlow.asSharedFlow()
    }

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            try {
                _mainLoading.value = true
                val response = StaffManagementRepository.staffManagementApi.login(
                    loginRequestBody = LoginRequest(
                        email, password
                    )
                )
                if (response.error.isNullOrEmpty() && !response.token.isNullOrEmpty()) {
                    _loginResponse.emit(response)
                } else {
                    _errorFlow.emit(response.error ?: "") //TODO get unknown error from string xml
                }
                _mainLoading.value = false
            } catch (e: Exception) {
                Log.e("Login", "Login error: " + e.message)
                _mainLoading.value = false
                _errorFlow.emit(e.message ?: "") //TODO get unknown error from string xml
            }
        }
    }
}