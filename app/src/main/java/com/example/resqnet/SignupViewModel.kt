package com.example.resqnet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    var fullName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var userType by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false)
        private set

    var isConfirmPasswordVisible by mutableStateOf(false)
        private set

    var fullNameError by mutableStateOf<String?>(null)
        private set

    var emailError by mutableStateOf<String?>(null)
        private set

    var phoneError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var confirmPasswordError by mutableStateOf<String?>(null)
        private set

    var userTypeError by mutableStateOf<String?>(null)
        private set

    fun onFullNameChange(value: String) {
        fullName = value
        if (fullNameError != null) fullNameError = null
    }

    fun onEmailChange(value: String) {
        email = value
        if (emailError != null) emailError = null
    }

    fun onPhoneChange(value: String) {
        phone = value.filter { it.isDigit() }.take(10)
        if (phoneError != null) phoneError = null
    }

    fun onPasswordChange(value: String) {
        password = value
        if (passwordError != null) passwordError = null
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
        if (confirmPasswordError != null) confirmPasswordError = null
    }

    fun onUserTypeChange(value: String) {
        userType = value
        if (userTypeError != null) userTypeError = null
    }

    fun onPasswordVisibilityChange() {
        isPasswordVisible = !isPasswordVisible
    }

    fun onConfirmPasswordVisibilityChange() {
        isConfirmPasswordVisible = !isConfirmPasswordVisible
    }

    fun validateSignup(): Boolean {
        var isValid = true

        if (fullName.isBlank()) {
            fullNameError = "Full name is required"
            isValid = false
        } else {
            fullNameError = null
        }

        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Enter a valid email"
            isValid = false
        } else {
            emailError = null
        }

        if (phone.length != 10) {
            phoneError = "Enter a valid 10-digit number"
            isValid = false
        } else {
            phoneError = null
        }

        if (password.length < 8) {
            passwordError = "Minimum 8 characters required"
            isValid = false
        } else {
            passwordError = null
        }

        if (confirmPassword.isBlank()) {
            confirmPasswordError = "Please confirm your password"
            isValid = false
        } else if (password != confirmPassword) {
            confirmPasswordError = "Passwords do not match"
            isValid = false
        } else {
            confirmPasswordError = null
        }

        if (userType.isBlank()) {
            userTypeError = "Please select a role"
            isValid = false
        } else {
            userTypeError = null
        }

        return isValid
    }
}
