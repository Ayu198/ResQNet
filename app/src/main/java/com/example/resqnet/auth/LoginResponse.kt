package com.example.resqnet.auth

data class LoginResponse (
    val token:String,
    val tokenType:String,
    val userId:Long,
    val fullName:String,
    val userType: UserType,
    val volunteerType: VolunteerType,
    val approvalStatus: ApprovalStatus,
    val message: String
)