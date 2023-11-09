package com.example.staffmanagementapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String? = null,
    val error: String? = null
)
