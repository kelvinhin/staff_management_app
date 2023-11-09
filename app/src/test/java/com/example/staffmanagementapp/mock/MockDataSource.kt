package com.example.staffmanagementapp.mock

import com.example.staffmanagementapp.data.request.LoginRequest
import com.example.staffmanagementapp.data.response.LoginResponse
import com.example.staffmanagementapp.data.response.StaffListResponse

object MockDataSource {
    val loginRequest = LoginRequest(
        email = "a.b@url.com",
        password = "Test5678"
    )
    val successLoginResponse = LoginResponse(token = "Abcd1234")
    val failedLoginResponse = LoginResponse(error = "Timeout")
    val staffList = StaffListResponse(
        page = 1,
        perPage = 6,
        total = 12,
        totalPages = 2,
        data = listOf(
            StaffListResponse.Data(
                id = 1,
                email = "a.b@url.com",
                firstName = "a",
                lastName = "b",
                avatar = "img1"
            ),
            StaffListResponse.Data(
                id = 2,
                email = "c.d@url.com",
                firstName = "c",
                lastName = "d",
                avatar = "img2"
            ),
            StaffListResponse.Data(
                id = 3,
                email = "e.f@url.com",
                firstName = "e",
                lastName = "f",
                avatar = "img3"
            ),
            StaffListResponse.Data(
                id = 4,
                email = "g.h@url.com",
                firstName = "g",
                lastName = "h",
                avatar = "img4"
            ),
            StaffListResponse.Data(
                id = 5,
                email = "i.j@url.com",
                firstName = "i",
                lastName = "j",
                avatar = "img5"
            ),
            StaffListResponse.Data(
                id = 6,
                email = "k.l@url.com",
                firstName = "k",
                lastName = "l",
                avatar = "img6"
            )
        )
    )
}