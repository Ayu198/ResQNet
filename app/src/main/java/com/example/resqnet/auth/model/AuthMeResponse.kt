package com.example.resqnet.auth.model

data class AuthMeResponse(
    val userId: Long,
    val fullName: String,
    val phoneNumber: String,
    val userType: UserType,
    val email: String,
    val volunteerType: VolunteerType? = null,
    val approvalStatus: ApprovalStatus? = null,
    val isPhoneVerified: Boolean,
    val isActive: Boolean,
    val message: String
)