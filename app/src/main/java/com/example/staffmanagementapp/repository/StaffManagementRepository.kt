package com.example.staffmanagementapp.repository

import com.example.staffmanagementapp.api.StaffManagementApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object StaffManagementRepository {
    private val baseUrl = "https://reqres.in/api/"

    val staffManagementApi: StaffManagementApi = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
        .create(StaffManagementApi::class.java)
}