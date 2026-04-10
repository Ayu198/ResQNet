package com.example.resqnet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginScreen(
    viewModel: LoginViewModel = viewModel(),
    navController: NavController,
    onBackClick: () -> Unit
) {

    val context = LocalContext.current

    val orangeStart = Color(0xFFFF7A00)
    val orangeEnd = Color(0xFFFFA24C)
    val screenBg = Color(0xFFFFF8F2)
    val cardBg = Color(0xFFFFFCFA)
    val fieldBg = Color(0xFFFFF2E7)
    val borderColor = Color(0xFFFFD2AD)
    val textPrimary = Color(0xFF2F241D)
    val textSecondary = Color(0xFF6E6258)
    val hintColor = Color(0xFF8C7E72)

    Scaffold(
        containerColor = screenBg,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Welcome back",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        colors = listOf(orangeStart, orangeEnd)
                    )
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(screenBg)
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(orangeStart, orangeEnd)
                        )
                    )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = "Log in to respond faster when every second matters.",
                        color = textSecondary,
                        fontSize = 15.sp,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = "Phone number",
                        color = textSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp, end = 10.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 16.dp)
                        ) {
                            Text(
                                text = "+91",
                                color = textPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = viewModel.phone,
                                onValueChange = viewModel::onPhoneChange,
                                placeholder = {
                                    Text(
                                        text = "Enter mobile number",
                                        color = hintColor
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(20.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Phone
                                ),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = fieldBg,
                                    unfocusedContainerColor = fieldBg,
                                    disabledContainerColor = fieldBg,
                                    focusedBorderColor = orangeStart,
                                    unfocusedBorderColor = borderColor,
                                    focusedTextColor = textPrimary,
                                    unfocusedTextColor = textPrimary,
                                    cursorColor = orangeStart
                                ),
                                isError = viewModel.phoneError != null,
                                modifier = Modifier.fillMaxWidth()
                            )

                            if (viewModel.phoneError != null) {
                                Text(
                                    text = viewModel.phoneError!!,
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Password",
                        color = textSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = viewModel.password,
                        onValueChange = viewModel::onPasswordChange,
                        placeholder = {
                            Text(
                                text = "Enter password",
                                color = hintColor
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp),
                        visualTransformation = if (viewModel.isPasswordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = {
                            IconButton(onClick = viewModel::onPasswordVisibilityChange) {
                                Icon(
                                    imageVector = if (viewModel.isPasswordVisible) {
                                        Icons.Default.Visibility
                                    } else {
                                        Icons.Default.VisibilityOff
                                    },
                                    contentDescription = "Toggle password visibility",
                                    tint = textSecondary
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = fieldBg,
                            unfocusedContainerColor = fieldBg,
                            disabledContainerColor = fieldBg,
                            focusedBorderColor = orangeStart,
                            unfocusedBorderColor = borderColor,
                            focusedTextColor = textPrimary,
                            unfocusedTextColor = textPrimary,
                            cursorColor = orangeStart
                        ),
                        isError = viewModel.passwordError != null,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (viewModel.passwordError != null) {
                        Text(
                            text = viewModel.passwordError!!,
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(
                        onClick = { navController.navigate(Screen.ForgotPasswordScreen.route) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(
                            text = "Forgot password?",
                            color = orangeStart,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            if (viewModel.validate()) {
                                if(isLocationReady(context) && isNotificationReady(context)) {
                                    navController.navigate(Screen.VolunteerHomeScreen.route)
                                } else {
                                    navController.navigate(Screen.PermissionCheckScreen.route)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(22.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = orangeStart
                        )
                    ) {
                        Text(
                            text = "Log in",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "By logging in, you agree to the Terms and Privacy Policy",
                        color = hintColor,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    TextButton(
                        onClick = {
                            navController.navigate(Screen.SignupScreen.route)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Create an account",
                            color = orangeStart,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
