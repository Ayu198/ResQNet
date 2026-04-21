package com.example.resqnet.auth

data class ResetPasswordResponse(
    val phoneNumber: String,
    val message: String
)
