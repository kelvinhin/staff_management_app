package com.example.staffmanagementapp

import com.example.staffmanagementapp.mock.MockApi
import com.example.staffmanagementapp.mock.MockDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals

class StaffManagementApiTest {
    private val api = MockApi()

    @Test
    fun loginSuccessTest() =
        runTest {
            assertEquals(
                MockDataSource.successLoginResponse,
                api.login(loginRequestBody = MockDataSource.loginRequest)
            )
        }

    @Test
    fun loginFailTest() =
        runTest {
            assertEquals(
                MockDataSource.failedLoginResponse,
                api.login(delay = 10, loginRequestBody = MockDataSource.loginRequest)
            )
        }

    @Test
    fun getStaffListTest() =
        runTest {
            assertEquals(
                MockDataSource.staffList,
                api.getStaffList(page = 1)
            )
        }
}