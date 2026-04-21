package com.example.resqnet.auth.model

data class SendOtpRequest(
    val phoneNumber : String,
    val purpose: OtpPurpose
)
