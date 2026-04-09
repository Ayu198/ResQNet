package com.example.resqnet

sealed class Screen(val route: String) {
    object FrontScreen : Screen("front_screen")
    object LoginScreen : Screen("login_screen")
    object SignupScreen : Screen("signup_screen")

    object ForgotPasswordScreen : Screen("forgot_password_screen")
    object ForgotPasswordOtpScreen : Screen("forgot_password_otp_screen")
    object ResetPasswordScreen : Screen("reset_password_screen")

    object SignupOtpScreen : Screen("signup_otp_screen")
    object HomeScreen : Screen("home_screen")
    object SelectSkillScreen : Screen("selectSkillsScreen")
    object ExperienceBackgroundScreen : Screen("experienceBackgroundScreen")
    object CertificationUploadScreen : Screen("certificationUploadScreen")
    object VolunteerSignupOtpScreen : Screen("VolunteerSignupOtpScreen")
    object ApplicationSubmittedScreen : Screen("applicationSubmittedScreen")
    object PermissionCheckScreen : Screen("permissionCheckScreen")
}
