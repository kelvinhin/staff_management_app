package com.example.staffmanagementapp.api

import com.example.staffmanagementapp.data.request.LoginRequest
import com.example.staffmanagementapp.data.response.LoginResponse
import com.example.staffmanagementapp.data.response.StaffListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StaffManagementApi {
    @POST("login")
    suspend fun login(
        @Query("delay") delay: Int = 5,
        @Body loginRequestBody: LoginRequest
    ): LoginResponse

    @GET("users")
    suspend fun getStaffList(
        @Query("page") page: Int = 1,
        @Query("delay") delay: Int = 5
    ): StaffListResponse
}