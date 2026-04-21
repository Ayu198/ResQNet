package com.example.resqnet.auth

data class VerifyOtpResponse(
    val phoneNumber : String,
    val purpose : OtpPurpose,
    val verified: Boolean,
    val message : String
)
