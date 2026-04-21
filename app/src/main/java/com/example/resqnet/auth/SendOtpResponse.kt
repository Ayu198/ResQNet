package com.example.resqnet.auth

data class SendOtpResponse(
    val phoneNumber : String,
    val purpose : OtpPurpose,
    val otp : String? = null,
    val message : String
)