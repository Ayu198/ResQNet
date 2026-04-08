package com.example.resqnet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var phone by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false)
        private set

    var phoneError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    fun onPhoneChange(value: String) {
        phone = value.filter { it.isDigit() }.take(10)
        if (phoneError != null) phoneError = null
    }

    fun onPasswordChange(value: String) {
        password = value
        if (passwordError != null) passwordError = null
    }

    fun onPasswordVisibilityChange() {
        isPasswordVisible = !isPasswordVisible
    }

    fun validate(): Boolean {
        var isValid = true

        if (phone.isBlank()) {
            phoneError = "Phone number cannot be empty"
            isValid = false
        } else if (phone.length != 10) {
            phoneError = "Phone number must be 10 digits"
            isValid = false
        } else {
            phoneError = null
        }

        if (password.isBlank()) {
            passwordError = "Password cannot be empty"
            isValid = false
        } else {
            passwordError = null
        }

        return isValid
    }
}
