package com.example.resqnet.auth.model

data class SendOtpResponse(
    val phoneNumber : String,
    val purpose : OtpPurpose,
    val otp : String? = null,
    val message : String
)