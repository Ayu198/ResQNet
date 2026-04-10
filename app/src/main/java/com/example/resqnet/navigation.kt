package com.example.resqnet

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navigation(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel = viewModel(),
    signupViewModel: SignupViewModel = viewModel(),
    forgotPasswordViewModel: ForgotPasswordViewModel = viewModel(),
    forgotPasswordOtpViewModel: OtpViewModel = viewModel(),
    signupOtpViewModel: OtpViewModel = viewModel(),
    volunteerViewModel : VolunteerOnboardingViewModel = viewModel(),
    otp : OtpViewModel = viewModel(),
    volunteerHomeScreenViewModel : VolunteerHomeViewModel = viewModel(),
    userViewModel: UserHomeViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.FrontScreen.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(420, easing = FastOutSlowInEasing),
                initialOffset = { it / 4 }
            ) + fadeIn(animationSpec = tween(320))
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(420, easing = FastOutSlowInEasing),
                targetOffset = { -it / 8 }
            ) + fadeOut(animationSpec = tween(280))
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(420, easing = FastOutSlowInEasing),
                initialOffset = { -it / 4 }
            ) + fadeIn(animationSpec = tween(320))
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(420, easing = FastOutSlowInEasing),
                targetOffset = { it / 8 }
            ) + fadeOut(animationSpec = tween(280))
        }
    ) {
        composable(Screen.FrontScreen.route) {
            frontScreen(navController)
        }

        composable(Screen.LoginScreen.route) {
            loginScreen(
                viewModel = loginViewModel,
                navController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.SignupScreen.route) {
            signupScreen(
                viewModel = signupViewModel,
                volunteerViewModel = volunteerViewModel,
                navController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.ForgotPasswordScreen.route) {
            forgotPasswordScreen(
                viewModel = forgotPasswordViewModel,
                navController = navController,
                onBackClick = { navController.popBackStack() },
                onSendOtp = {
                    forgotPasswordOtpViewModel.clearOtp()
                    navController.navigate(Screen.ForgotPasswordOtpScreen.route)
                }
            )
        }

        composable(Screen.ForgotPasswordOtpScreen.route) {
            otpVerificationScreen(
                viewModel = forgotPasswordOtpViewModel,
                title = "Verify code",
                subtitle = "Enter the 6-digit code sent to your registered mobile number.",
                onBackClick = { navController.popBackStack() },
                onVerifyOtp = {
                    navController.navigate(Screen.ResetPasswordScreen.route)
                },
                onResendOtp = {
                    // resend forgot-password OTP
                }
            )
        }

        composable(Screen.SignupOtpScreen.route) {
            otpVerificationScreen(
                viewModel = signupOtpViewModel,
                title = "Verify account",
                subtitle = "Enter the 6-digit code sent to your mobile number to complete signup.",
                onBackClick = { navController.popBackStack() },
                onVerifyOtp = {
                    navController.navigate(Screen.UserHomeScreen.route) {
                        popUpTo(Screen.FrontScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onResendOtp = {
                    // resend signup OTP
                }
            )
        }

        composable(Screen.ResetPasswordScreen.route) {
            resetPasswordScreen(
                viewModel = forgotPasswordViewModel,
                navController = navController,
                onBackClick = { navController.popBackStack() },
                onPasswordUpdated = {
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(Screen.FrontScreen.route)
                    }
                }
            )
        }

        composable(Screen.UserHomeScreen.route) {
            userHomeScreen(
                navController = navController,
                viewModel = userViewModel,
                onSosClick = {/*Todo*/},
                onContactsClick = {/*Todo*/},
                onHospitalsClick = {/*Todo*/},
                onHistoryClick = {/*Todo*/},
                onProfileClick = {/*Todo*/}
            )
        }
        composable(Screen.SelectSkillScreen.route) {
            selectSkillsScreen(
                onboardingViewModel = volunteerViewModel,
                onBackClick = { navController.popBackStack() },
                onContinueClick = {
                    navController.navigate(Screen.ExperienceBackgroundScreen.route)
                }
            )
        }
        composable (Screen.ExperienceBackgroundScreen.route) {
            experienceBackgroundScreen(
                onboardingViewModel = volunteerViewModel,
                onBackClick = { navController.popBackStack() },
                onContinueClick = {
                    if(volunteerViewModel.volunteerType == VolunteerType.VERIFIED_MEDICAL){
                        navController.navigate(Screen.CertificationUploadScreen.route)
                    } else {
                        navController.navigate(Screen.VolunteerSignupOtpScreen.route)
                    }
                }
            )
        }
        composable (Screen.CertificationUploadScreen.route) {
            certificationUploadScreen(
                onboardingViewModel = volunteerViewModel,
                onBackClick = { navController.popBackStack() },
                onContinueClick = {
                    navController.navigate(Screen.VolunteerSignupOtpScreen.route)
                }
            )
        }
        composable(Screen.VolunteerSignupOtpScreen.route) {
            otpVerificationScreen(
                viewModel = otp,
                title = "Verify account",
                subtitle = "Enter the 6-digit code sent to your mobile number to complete signup.",
                onBackClick = { navController.popBackStack() },
                onVerifyOtp = {
                    navController.navigate(Screen.ApplicationSubmittedScreen.route)
                }
            )
        }
        composable(Screen.ApplicationSubmittedScreen.route) {
            applicationSubmittedScreen(
                onDoneClick = {navController.navigate(Screen.LoginScreen.route)}
            )
        }
        composable(Screen.PermissionCheckScreen.route) {
            permissionCheckRoute(
                navController = navController,
                onContinueClick = {
                    navController.navigate(Screen.VolunteerHomeScreen.route) {
                        popUpTo(Screen.FrontScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable (Screen.VolunteerHomeScreen.route) {
            volunteerHomeScreen(
                navController = navController,
                viewModel = volunteerHomeScreenViewModel,
                onViewAlertDetails = {/*Todo*/},
                onAcceptAlert = {/*Todo*/},
                onAlertsClick = {/*Todo*/},
                onProfileClick = {/*Todo*/}
            )
        }
    }
}
