package com.example.resqnet

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resqnet.auth.network.RetrofitProvider
import com.example.resqnet.auth.repository.ApiResult
import com.example.resqnet.auth.repository.AuthRepository
import kotlinx.coroutines.launch

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

    var apiError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var forgotPasswordSuccess by mutableStateOf(false)
        private set

    var resetPasswordSuccess by mutableStateOf(false)
        private set

    fun onPhoneChange(value: String) {
        phone = value.filter { it.isDigit() }.take(10)
        if (phoneError != null) phoneError = null
        if (apiError != null) apiError = null
    }

    fun onNewPasswordChange(value: String) {
        newPassword = value
        if (newPasswordError != null) newPasswordError = null
        if (apiError != null) apiError = null
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
        if (confirmPasswordError != null) confirmPasswordError = null
        if (apiError != null) apiError = null
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

    fun forgotPassword(context: Context) {
        if (!validatePhone()) return

        viewModelScope.launch {
            isLoading = true
            apiError = null
            forgotPasswordSuccess = false

            val authRepository = AuthRepository(
                RetrofitProvider.provideAuthApiService(context.applicationContext)
            )

            when (val result = authRepository.forgotPassword(phone)) {
                is ApiResult.Success -> {
                    forgotPasswordSuccess = true
                }

                is ApiResult.Error -> {
                    apiError = result.message
                }
            }

            isLoading = false
        }
    }

    fun resetPassword(context: Context) {
        if (!validatePasswordReset()) return

        viewModelScope.launch {
            isLoading = true
            apiError = null
            resetPasswordSuccess = false

            val authRepository = AuthRepository(
                RetrofitProvider.provideAuthApiService(context.applicationContext)
            )

            when (val result = authRepository.resetPassword(phone, newPassword)) {
                is ApiResult.Success -> {
                    resetPasswordSuccess = true
                }

                is ApiResult.Error -> {
                    apiError = result.message
                }
            }

            isLoading = false
        }
    }

    fun consumeForgotPasswordSuccess() {
        forgotPasswordSuccess = false
    }

    fun consumeResetPasswordSuccess() {
        resetPasswordSuccess = false
    }

    fun clearResetFields() {
        newPassword = ""
        confirmPassword = ""
        newPasswordError = null
        confirmPasswordError = null
        apiError = null
    }
}
