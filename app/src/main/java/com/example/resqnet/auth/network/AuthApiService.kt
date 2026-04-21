package com.example.resqnet.auth.network

import com.example.resqnet.auth.model.AuthMeResponse
import com.example.resqnet.auth.model.ForgotPasswordRequest
import com.example.resqnet.auth.model.ForgotPasswordResponse
import com.example.resqnet.auth.model.LoginRequest
import com.example.resqnet.auth.model.LoginResponse
import com.example.resqnet.auth.model.ResetPasswordRequest
import com.example.resqnet.auth.model.ResetPasswordResponse
import com.example.resqnet.auth.model.SendOtpRequest
import com.example.resqnet.auth.model.SendOtpResponse
import com.example.resqnet.auth.model.SignupUserRequest
import com.example.resqnet.auth.model.SignupUserResponse
import com.example.resqnet.auth.model.VerifyOtpRequest
import com.example.resqnet.auth.model.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/auth/signup-user")
    suspend fun signupUser(@Body request: SignupUserRequest): Response<SignupUserResponse>

    @POST("api/auth/send-otp")
    suspend fun sendOtp(@Body request: SendOtpRequest): Response<SendOtpResponse>

    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Response<VerifyOtpResponse>

    @POST("api/auth/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<ForgotPasswordResponse>

    @POST("api/auth/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<ResetPasswordResponse>

    @GET("api/auth/me")
    suspend fun authMe(): Response<AuthMeResponse>
}
