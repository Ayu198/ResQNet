package com.example.resqnet.auth

import android.telephony.PhoneNumberUtils

data class VerifyOtpRequest(
    val phoneNumber : String,
    val otpCode : String,
    val purpose : OtpPurpose
)
