package com.example.resqnet

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun signupScreen(
    viewModel: SignupViewModel = viewModel(),
    volunteerViewModel : VolunteerOnboardingViewModel,
    navController: NavController,
    onBackClick: () -> Unit
) {
    var showVolunteerTypeSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val blurAmount by animateDpAsState(
        targetValue = if (showVolunteerTypeSheet) 16.dp else 0.dp,
        animationSpec = tween(220),
        label = "blurAmount"
    )

    val orangeStart = Color(0xFFFF7A00)
    val orangeEnd = Color(0xFFFFA24C)
    val screenBg = Color(0xFFFFF8F2)
    val cardBg = Color(0xFFFFFCFA)
    val fieldBg = Color(0xFFFFF2E7)
    val borderColor = Color(0xFFFFD2AD)
    val selectedCardBg = Color(0xFFFFEDD9)
    val textPrimary = Color(0xFF2F241D)
    val textSecondary = Color(0xFF6E6258)
    val hintColor = Color(0xFF8C7E72)

    LaunchedEffect(viewModel.signupSuccess) {
        if (viewModel.signupSuccess) {
            navController.navigate(Screen.SignupOtpScreen.route)
            viewModel.consumeSignupSuccess()
        }
    }

    Scaffold(
        containerColor = screenBg,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create account",
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
                    .fillMaxSize()
                    .blur(blurAmount)
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
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 24.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 20.dp, vertical = 24.dp)
                    ) {
                        Text(
                            text = "Join the network as a volunteer or a person seeking nearby help.",
                            color = textSecondary,
                            fontSize = 15.sp,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        AppTextField(
                            label = "Full name",
                            value = viewModel.fullName,
                            onValueChange = viewModel::onFullNameChange,
                            placeholder = "Enter your full name",
                            error = viewModel.fullNameError,
                            textPrimary = textPrimary,
                            hintColor = hintColor,
                            fieldBg = fieldBg,
                            borderColor = borderColor,
                            accent = orangeStart
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        AppTextField(
                            label = "Email address",
                            value = viewModel.email,
                            onValueChange = viewModel::onEmailChange,
                            placeholder = "Enter your email",
                            error = viewModel.emailError,
                            keyboardType = KeyboardType.Email,
                            textPrimary = textPrimary,
                            hintColor = hintColor,
                            fieldBg = fieldBg,
                            borderColor = borderColor,
                            accent = orangeStart
                        )

                        Spacer(modifier = Modifier.height(16.dp))

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

                        Spacer(modifier = Modifier.height(16.dp))

                        PasswordField(
                            label = "Create password",
                            value = viewModel.password,
                            onValueChange = viewModel::onPasswordChange,
                            placeholder = "Minimum 8 characters",
                            isVisible = viewModel.isPasswordVisible,
                            onToggle = viewModel::onPasswordVisibilityChange,
                            error = viewModel.passwordError,
                            textPrimary = textPrimary,
                            textSecondary = textSecondary,
                            hintColor = hintColor,
                            fieldBg = fieldBg,
                            borderColor = borderColor,
                            accent = orangeStart
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        PasswordField(
                            label = "Confirm password",
                            value = viewModel.confirmPassword,
                            onValueChange = viewModel::onConfirmPasswordChange,
                            placeholder = "Re-enter password",
                            isVisible = viewModel.isConfirmPasswordVisible,
                            onToggle = viewModel::onConfirmPasswordVisibilityChange,
                            error = viewModel.confirmPasswordError,
                            textPrimary = textPrimary,
                            textSecondary = textSecondary,
                            hintColor = hintColor,
                            fieldBg = fieldBg,
                            borderColor = borderColor,
                            accent = orangeStart
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Register as",
                            color = textSecondary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            RoleCard(
                                title = "Volunteer",
                                subtitle = "Can respond to alerts",
                                isSelected = viewModel.userType == "VOLUNTEER",
                                onClick = { viewModel.onUserTypeChange("VOLUNTEER") },
                                selectedColor = orangeStart,
                                selectedBg = selectedCardBg,
                                textPrimary = textPrimary,
                                textSecondary = textSecondary,
                                modifier = Modifier.weight(1f)
                            )

                            RoleCard(
                                title = "Need help",
                                subtitle = "Request support nearby",
                                isSelected = viewModel.userType == "USER",
                                onClick = { viewModel.onUserTypeChange("USER") },
                                selectedColor = orangeStart,
                                selectedBg = selectedCardBg,
                                textPrimary = textPrimary,
                                textSecondary = textSecondary,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        if (viewModel.userTypeError != null) {
                            Text(
                                text = viewModel.userTypeError!!,
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                            )
                        }
                        if (viewModel.apiError != null) {
                            Text(
                                text = viewModel.apiError!!,
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(28.dp))

                        Button(
                            onClick = {
                                if (viewModel.validateSignup()) {
                                    if (viewModel.userType == "VOLUNTEER") {
                                        showVolunteerTypeSheet = true
                                    } else {
                                        viewModel.signupUser(context)
                                    }
                                }
                            },
                            enabled = !viewModel.isLoading,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),
                            shape = RoundedCornerShape(22.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orangeStart
                            )
                        ) {
                            if (viewModel.isLoading) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(
                                    text = if (viewModel.userType == "VOLUNTEER") "Next" else "Create account",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }


                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            if (showVolunteerTypeSheet) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.35f))
                        .clickable { showVolunteerTypeSheet = false }
                )

                VolunteerTypeSheet(
                    modifier = Modifier.align(Alignment.Center),
                    onDismiss = { showVolunteerTypeSheet = false },
                    onVerifiedMedical = {
                        showVolunteerTypeSheet = false
                        volunteerViewModel.updateVolunteerType(VolunteerType.VERIFIED_MEDICAL)
                        navController.navigate(Screen.SelectSkillScreen.route)
                    },
                    onCommunityHelper = {
                        showVolunteerTypeSheet = false
                        volunteerViewModel.updateVolunteerType(VolunteerType.COMMUNITY_HELPER)
                        navController.navigate(Screen.SelectSkillScreen.route)
                    }
                )
            }
        }
    }
}

@Composable
private fun AppTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    error: String?,
    textPrimary: Color,
    hintColor: Color,
    fieldBg: Color,
    borderColor: Color,
    accent: Color,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Text(
        text = label,
        color = Color(0xFF6E6258),
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = hintColor
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = fieldBg,
            unfocusedContainerColor = fieldBg,
            disabledContainerColor = fieldBg,
            focusedBorderColor = accent,
            unfocusedBorderColor = borderColor,
            focusedTextColor = textPrimary,
            unfocusedTextColor = textPrimary,
            cursorColor = accent
        ),
        isError = error != null,
        modifier = Modifier.fillMaxWidth()
    )

    if (error != null) {
        Text(
            text = error,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
        )
    }
}

@Composable
private fun PasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isVisible: Boolean,
    onToggle: () -> Unit,
    error: String?,
    textPrimary: Color,
    textSecondary: Color,
    hintColor: Color,
    fieldBg: Color,
    borderColor: Color,
    accent: Color
) {
    Text(
        text = label,
        color = Color(0xFF6E6258),
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = hintColor
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        visualTransformation = if (isVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(onClick = onToggle) {
                Icon(
                    imageVector = if (isVisible) {
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
            focusedBorderColor = accent,
            unfocusedBorderColor = borderColor,
            focusedTextColor = textPrimary,
            unfocusedTextColor = textPrimary,
            cursorColor = accent
        ),
        isError = error != null,
        modifier = Modifier.fillMaxWidth()
    )

    if (error != null) {
        Text(
            text = error,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
        )
    }
}

@Composable
private fun RoleCard(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    selectedColor: Color,
    selectedBg: Color,
    textPrimary: Color,
    textSecondary: Color,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) selectedBg else Color(0xFFFFF5EC)
        ),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(2.dp, selectedColor)
        } else {
            androidx.compose.foundation.BorderStroke(1.dp, Color.Transparent)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = if (isSelected) selectedColor else textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subtitle,
                color = textSecondary,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun VolunteerTypeSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onVerifiedMedical: () -> Unit,
    onCommunityHelper: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.18f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.14f))
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(width = 88.dp, height = 6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.35f))
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Choose volunteer type",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Select the option that best matches your training and role in emergency response.",
                color = Color.White.copy(alpha = 0.88f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            GlassOptionCard(
                title = "Verified Medical Volunteer",
                subtitle = "For doctors, nurses, paramedics, and users with formal medical proof.",
                onClick = onVerifiedMedical,
                highlight = true
            )

            Spacer(modifier = Modifier.height(14.dp))

            GlassOptionCard(
                title = "Community Helper",
                subtitle = "For basic first aid, CPR awareness, transport help, and scene support.",
                onClick = onCommunityHelper,
                highlight = false
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.18f)
                )
            ) {
                Text(
                    text = "Cancel",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun GlassOptionCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    highlight: Boolean
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (highlight) {
                Color.White.copy(alpha = 0.24f)
            } else {
                Color.White.copy(alpha = 0.14f)
            }
        )
    ) {
        Column(
            modifier = Modifier
                .background(Color.White.copy(alpha = if (highlight) 0.16f else 0.10f))
                .padding(18.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.88f),
                fontSize = 13.sp,
                lineHeight = 19.sp
            )
        }
    }
}
