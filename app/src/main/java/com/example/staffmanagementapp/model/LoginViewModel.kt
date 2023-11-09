package com.example.staffmanagementapp.model

import androidx.lifecycle.ViewModel
import com.example.staffmanagementapp.data.response.LoginResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _mainLoading: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }

    val mainLoading: StateFlow<Boolean> by lazy {
        _mainLoading.asStateFlow()
    }

    private val _loginResponse: MutableSharedFlow<LoginResponse> by lazy {
        MutableSharedFlow()
    }

    val loginResponse: SharedFlow<LoginResponse> by lazy {
        _loginResponse.asSharedFlow()
    }
}