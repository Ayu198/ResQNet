package com.example.resqnet.auth.model

data class SignupUserResponse(
    val userId:Long,
    val fullName : String,
    val phoneNumber : String,
    val userType: UserType,
    val email : String,
    val message : String
)
