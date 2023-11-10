package com.example.staffmanagementapp.tools

import androidx.core.util.PatternsCompat
import com.example.staffmanagementapp.constants.Constants

fun CharSequence?.isValidEmail() = !isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun CharSequence?.isValidPassword() = !isNullOrEmpty() && Constants.PW_REGEX.toRegex().matches(this)