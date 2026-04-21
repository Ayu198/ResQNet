package com.example.resqnet.auth

data class SendOtpRequest(
    val phoneNumber : String,
    val purpose: OtpPurpose
)
