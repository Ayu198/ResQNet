package com.example.resqnet.auth.model

data class ApiErrorResponse(
    val timeStamp: String? = null,
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null,
    val path: String? = null
)