package com.example.resqnet

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resqnet.auth.model.OtpPurpose
import com.example.resqnet.auth.network.RetrofitProvider
import com.example.resqnet.auth.repository.ApiResult
import com.example.resqnet.auth.repository.AuthRepository
import kotlinx.coroutines.launch

class OtpViewModel : ViewModel() {

    var otp by mutableStateOf("")
        private set

    var otpError by mutableStateOf<String?>(null)
        private set

    var apiError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var verificationSuccess by mutableStateOf(false)
        private set

    var otpSentSuccessfully by mutableStateOf(false)
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var purpose by mutableStateOf<OtpPurpose?>(null)
        private set

    fun onOtpChange(value: String) {
        otp = value.filter { it.isDigit() }.take(6)
        if (otpError != null) otpError = null
        if (apiError != null) apiError = null
    }

    fun validateOtp(): Boolean {
        return if (otp.length != 6) {
            otpError = "Enter the 6-digit OTP"
            false
        } else {
            otpError = null
            true
        }
    }

    fun configure(phoneNumber: String, purpose: OtpPurpose) {
        this.phoneNumber = phoneNumber
        this.purpose = purpose
    }

    fun sendOtp(context: Context) {
        val otpPurpose = purpose ?: return
        if (phoneNumber.isBlank()) return

        viewModelScope.launch {
            isLoading = true
            apiError = null
            otpSentSuccessfully = false

            val authRepository = AuthRepository(
                RetrofitProvider.provideAuthApiService(context.applicationContext)
            )

            when (val result = authRepository.sendOtp(phoneNumber, otpPurpose)) {
                is ApiResult.Success -> {
                    otpSentSuccessfully = true
                }

                is ApiResult.Error -> {
                    apiError = result.message
                }
            }

            isLoading = false
        }
    }

    fun verifyOtp(context: Context) {
        val otpPurpose = purpose ?: return
        if (!validateOtp()) return

        viewModelScope.launch {
            isLoading = true
            apiError = null
            verificationSuccess = false

            val authRepository = AuthRepository(
                RetrofitProvider.provideAuthApiService(context.applicationContext)
            )

            when (val result = authRepository.verifyOtp(phoneNumber, otp, otpPurpose)) {
                is ApiResult.Success -> {
                    verificationSuccess = true
                }

                is ApiResult.Error -> {
                    apiError = result.message
                }
            }

            isLoading = false
        }
    }

    fun consumeVerificationSuccess() {
        verificationSuccess = false
    }

    fun clearOtp() {
        otp = ""
        otpError = null
        apiError = null
        verificationSuccess = false
        otpSentSuccessfully = false
    }
}
