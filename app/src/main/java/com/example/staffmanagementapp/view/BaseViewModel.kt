package com.example.staffmanagementapp.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel: ViewModel() {
    protected open val _mainLoading: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }
    open val mainLoading: StateFlow<Boolean> by lazy {
        _mainLoading.asStateFlow()
    }

    protected open val _errorFlow: MutableSharedFlow<String> by lazy {
        MutableSharedFlow()
    }
    open val errorFlow by lazy {
        _errorFlow.asSharedFlow()
    }

    fun showMainLoading() {
        _mainLoading.value = true
    }

    fun hideMainLoading() {
        _mainLoading.value = false
    }
}