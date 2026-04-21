package com.example.resqnet.auth.model

data class ResetPasswordResponse(
    val phoneNumber: String,
    val message: String
)
