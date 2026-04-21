package com.example.resqnet.auth

data class ApiErrorResponse(
    val timeStamp: String? = null,
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null,
    val path: String? = null
)