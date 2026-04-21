package com.example.resqnet.auth

data class ResetPasswordRequest(
    val phoneNumber: String,
    val newPassword: String
)
