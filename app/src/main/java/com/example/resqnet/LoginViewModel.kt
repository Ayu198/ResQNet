package com.example.resqnet

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resqnet.auth.model.ApprovalStatus
import com.example.resqnet.auth.model.UserType
import com.example.resqnet.auth.network.RetrofitProvider
import com.example.resqnet.auth.repository.ApiResult
import com.example.resqnet.auth.repository.AuthRepository
import com.example.resqnet.auth.session.SessionManager
import kotlinx.coroutines.launch

enum class LoginDestination {
    USER_HOME,
    VOLUNTEER_HOME,
    VOLUNTEER_PENDING
}

class LoginViewModel : ViewModel() {

    var phone by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isPasswordVisible by mutableStateOf(false)
        private set

    var phoneError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var apiError by mutableStateOf<String?>(null)
        private set

    var loginDestination by mutableStateOf<LoginDestination?>(null)
        private set

    fun onPhoneChange(value: String) {
        phone = value.filter { it.isDigit() }.take(10)
        if (phoneError != null) phoneError = null
        if (apiError != null) apiError = null
    }

    fun onPasswordChange(value: String) {
        password = value
        if (passwordError != null) passwordError = null
        if (apiError != null) apiError = null
    }

    fun onPasswordVisibilityChange() {
        isPasswordVisible = !isPasswordVisible
    }

    fun validate(): Boolean {
        var isValid = true

        if (phone.isBlank()) {
            phoneError = "Phone number cannot be empty"
            isValid = false
        } else if (phone.length != 10) {
            phoneError = "Phone number must be 10 digits"
            isValid = false
        } else {
            phoneError = null
        }

        if (password.isBlank()) {
            passwordError = "Password cannot be empty"
            isValid = false
        } else {
            passwordError = null
        }

        return isValid
    }

    fun login(context: Context) {
        if (!validate()) return

        viewModelScope.launch {
            isLoading = true
            apiError = null
            loginDestination = null

            val appContext = context.applicationContext
            val apiService = RetrofitProvider.provideAuthApiService(appContext)
            val authRepository = AuthRepository(apiService)
            val sessionManager = SessionManager(appContext)

            when (val loginResult = authRepository.login(phone, password)) {
                is ApiResult.Success -> {
                    sessionManager.saveToken(loginResult.data.token)

                    when (val meResult = authRepository.authMe()) {
                        is ApiResult.Success -> {
                            loginDestination = when {
                                meResult.data.userType == UserType.NORMAL_USER -> {
                                    LoginDestination.USER_HOME
                                }

                                meResult.data.userType == UserType.VOLUNTEER &&
                                        meResult.data.approvalStatus == ApprovalStatus.APPROVED -> {
                                    LoginDestination.VOLUNTEER_HOME
                                }

                                meResult.data.userType == UserType.VOLUNTEER &&
                                        meResult.data.approvalStatus == ApprovalStatus.PENDING -> {
                                    LoginDestination.VOLUNTEER_PENDING
                                }

                                else -> {
                                    apiError = "Your volunteer application is not approved."
                                    null
                                }
                            }
                        }

                        is ApiResult.Error -> {
                            sessionManager.clearSession()
                            apiError = meResult.message
                        }
                    }
                }

                is ApiResult.Error -> {
                    apiError = loginResult.message
                }
            }

            isLoading = false
        }
    }

    fun clearNavigationDestination() {
        loginDestination = null
    }
}