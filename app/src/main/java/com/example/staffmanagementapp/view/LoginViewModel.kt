package com.example.staffmanagementapp.view

import androidx.lifecycle.viewModelScope
import com.example.staffmanagementapp.data.request.LoginRequest
import com.example.staffmanagementapp.data.response.LoginResponse
import com.example.staffmanagementapp.repository.StaffManagementRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class LoginViewModel : BaseViewModel() {
    private val _loginResponse: MutableSharedFlow<LoginResponse> by lazy {
        MutableSharedFlow()
    }

    val loginResponse by lazy {
        _loginResponse.asSharedFlow()
    }

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            try {
                showMainLoading()
                val response = StaffManagementRepository.staffManagementApi.login(
                    loginRequestBody = LoginRequest(
                        email, password
                    )
                )
                if (!response.token.isNullOrEmpty()) {
                    _loginResponse.emit(response)
                }
                hideMainLoading()
            } catch (e: HttpException) {
                hideMainLoading()
                _errorFlow.emit(e.response()?.errorBody()?.string() ?: e.message())
            } catch (e: Exception) {
                hideMainLoading()
                _errorFlow.emit(e.message ?: "")
            }
        }
    }
}