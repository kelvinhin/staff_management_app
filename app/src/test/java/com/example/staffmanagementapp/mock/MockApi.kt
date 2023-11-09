package com.example.staffmanagementapp.mock

import com.example.staffmanagementapp.api.StaffManagementApi
import com.example.staffmanagementapp.data.request.LoginRequest
import com.example.staffmanagementapp.data.response.LoginResponse
import com.example.staffmanagementapp.data.response.StaffListResponse

class MockApi: StaffManagementApi {
    override suspend fun login(delay: Int, loginRequestBody: LoginRequest): LoginResponse {
        return if (delay >= 10) {
            MockDataSource.failedLoginResponse
        } else {
            MockDataSource.successLoginResponse
        }
    }

    override suspend fun getStaffList(page: Int): StaffListResponse {
        return MockDataSource.staffList
    }

}