package com.example.resqnet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ForgotPasswordViewModel : ViewModel() {

    var phone by mutableStateOf("")
        private set

    var newPassword by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var isNewPasswordVisible by mutableStateOf(false)
        private set

    var isConfirmPasswordVisible by mutableStateOf(false)
        private set

    var phoneError by mutableStateOf<String?>(null)
        private set

    var newPasswordError by mutableStateOf<String?>(null)
        private set

    var confirmPasswordError by mutableStateOf<String?>(null)
        private set

    fun onPhoneChange(value: String) {
        phone = value.filter { it.isDigit() }.take(10)
        if (phoneError != null) phoneError = null
    }

    fun onNewPasswordChange(value: String) {
        newPassword = value
        if (newPasswordError != null) newPasswordError = null
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
        if (confirmPasswordError != null) confirmPasswordError = null
    }

    fun toggleNewPasswordVisibility() {
        isNewPasswordVisible = !isNewPasswordVisible
    }

    fun toggleConfirmPasswordVisibility() {
        isConfirmPasswordVisible = !isConfirmPasswordVisible
    }

    fun validatePhone(): Boolean {
        return if (phone.length != 10) {
            phoneError = "Enter a valid 10-digit number"
            false
        } else {
            phoneError = null
            true
        }
    }

    fun validatePasswordReset(): Boolean {
        var isValid = true

        if (newPassword.length < 8) {
            newPasswordError = "Minimum 8 characters required"
            isValid = false
        } else {
            newPasswordError = null
        }

        if (confirmPassword.isBlank()) {
            confirmPasswordError = "Please confirm your password"
            isValid = false
        } else if (newPassword != confirmPassword) {
            confirmPasswordError = "Passwords do not match"
            isValid = false
        } else {
            confirmPasswordError = null
        }

        return isValid
    }
}
