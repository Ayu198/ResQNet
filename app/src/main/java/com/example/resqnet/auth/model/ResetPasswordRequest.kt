package com.example.resqnet.auth.model

data class ResetPasswordRequest(
    val phoneNumber: String,
    val newPassword: String
)
