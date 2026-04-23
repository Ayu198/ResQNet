package com.example.resqnet.auth.repository

import com.example.resqnet.auth.model.ApiErrorResponse
import com.example.resqnet.auth.model.AuthMeResponse
import com.example.resqnet.auth.model.ForgotPasswordRequest
import com.example.resqnet.auth.model.ForgotPasswordResponse
import com.example.resqnet.auth.model.LoginRequest
import com.example.resqnet.auth.model.LoginResponse
import com.example.resqnet.auth.model.OtpPurpose
import com.example.resqnet.auth.model.ResetPasswordRequest
import com.example.resqnet.auth.model.ResetPasswordResponse
import com.example.resqnet.auth.model.SendOtpRequest
import com.example.resqnet.auth.model.SendOtpResponse
import com.example.resqnet.auth.model.SignupUserRequest
import com.example.resqnet.auth.model.SignupUserResponse
import com.example.resqnet.auth.model.VerifyOtpRequest
import com.example.resqnet.auth.model.VerifyOtpResponse
import com.example.resqnet.auth.network.AuthApiService
import com.google.gson.Gson
import retrofit2.Response

class AuthRepository(
    private val apiService: AuthApiService
) {
    private val gson = Gson()

    suspend fun login(phoneNumber: String, password: String): ApiResult<LoginResponse> {
        return handleResponse(apiService.login(LoginRequest(phoneNumber, password)))
    }

    suspend fun signupUser(
        fullName: String,
        phoneNumber: String,
        email: String,
        password: String
    ): ApiResult<SignupUserResponse> {
        return handleResponse(
            apiService.signupUser(
                SignupUserRequest(
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    email = email,
                    password = password
                )
            )
        )
    }

    suspend fun sendOtp(phoneNumber: String, purpose: OtpPurpose): ApiResult<SendOtpResponse> {
        return handleResponse(apiService.sendOtp(SendOtpRequest(phoneNumber, purpose)))
    }

    suspend fun verifyOtp(
        phoneNumber: String,
        otpCode: String,
        purpose: OtpPurpose
    ): ApiResult<VerifyOtpResponse> {
        return handleResponse(apiService.verifyOtp(VerifyOtpRequest(phoneNumber, otpCode, purpose)))
    }

    suspend fun forgotPassword(phoneNumber: String): ApiResult<ForgotPasswordResponse> {
        return handleResponse(apiService.forgotPassword(ForgotPasswordRequest(phoneNumber)))
    }

    suspend fun resetPassword(phoneNumber: String, newPassword: String): ApiResult<ResetPasswordResponse> {
        return handleResponse(apiService.resetPassword(ResetPasswordRequest(phoneNumber, newPassword)))
    }

    suspend fun authMe(): ApiResult<AuthMeResponse> {
        return handleResponse(apiService.authMe())
    }

    private fun <T> handleResponse(response: Response<T>): ApiResult<T> {
        return try {
            if (response.isSuccessful && response.body() != null) {
                ApiResult.Success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string()
                    ?.let { raw ->
                        runCatching { gson.fromJson(raw, ApiErrorResponse::class.java) }.getOrNull()?.message
                    }
                    ?: "Something went wrong"

                ApiResult.Error(errorMessage)
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Unexpected error")
        }
    }
}
