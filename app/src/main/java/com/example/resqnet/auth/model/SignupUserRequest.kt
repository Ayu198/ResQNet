package com.example.resqnet.auth.model

data class SignupUserRequest(
    val fullName:String,
    val phoneNumber:String,
    val email :String,
    val password : String
)
