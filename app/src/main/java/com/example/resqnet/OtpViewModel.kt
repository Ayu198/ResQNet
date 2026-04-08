package com.example.resqnet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class OtpViewModel : ViewModel() {

    var otp by mutableStateOf("")
        private set

    var otpError by mutableStateOf<String?>(null)
        private set

    fun onOtpChange(value: String) {
        otp = value.filter { it.isDigit() }.take(6)
        if (otpError != null) otpError = null
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

    fun clearOtp() {
        otp = ""
        otpError = null
    }
}
