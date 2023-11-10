package com.example.staffmanagementapp

import com.example.staffmanagementapp.tools.isValidEmail
import com.example.staffmanagementapp.tools.isValidPassword
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginInputCheckTest {
    @Test
    fun emailCorrectFormatTest() =
        assertTrue(
            "eve.holt@reqres.in".isValidEmail()
        )

    @Test
    fun emailWrongFormatTest() =
        assertFalse(
            "eve.holt@reqres".isValidEmail()
        )

    @Test
    fun passwordCorrectFormatTest() =
        assertTrue(
            "cityslicka".isValidPassword() && "c1tySl1cka".isValidPassword()
        )

    @Test
    fun passwordWrongFormatTest() =
        assertFalse(
            "c!tySl1ck@".isValidPassword()
        )
}