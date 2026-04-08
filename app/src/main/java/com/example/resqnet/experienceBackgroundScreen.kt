package com.example.resqnet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun experienceBackgroundScreen(
    onboardingViewModel: VolunteerOnboardingViewModel = viewModel(),
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val orangeStart = Color(0xFFFF7A00)
    val orangeEnd = Color(0xFFFFA24C)
    val screenBg = Color(0xFFFFF8F2)
    val cardBg = Color(0xFFFFFCFA)
    val fieldBg = Color(0xFFFFF2E7)
    val borderColor = Color(0xFFFFD2AD)
    val selectedBg = Color(0xFFFFEDD9)
    val textPrimary = Color(0xFF2F241D)
    val textSecondary = Color(0xFF6E6258)
    val hintColor = Color(0xFF8C7E72)

    val title = when (onboardingViewModel.volunteerType) {
        VolunteerType.VERIFIED_MEDICAL -> "Experience & Background"
        VolunteerType.COMMUNITY_HELPER -> "Background & Training"
        null -> "Experience & Background"
    }

    val helperText = when (onboardingViewModel.volunteerType) {
        VolunteerType.VERIFIED_MEDICAL ->
            "Tell us about your medical background so we can review and verify your profile properly."
        VolunteerType.COMMUNITY_HELPER ->
            "Tell us about your training or background so we understand how you can help safely."
        null ->
            "Tell us about your background so we can review your volunteer profile properly."
    }

    val organizationLabel = when (onboardingViewModel.volunteerType) {
        VolunteerType.VERIFIED_MEDICAL -> "Organization / institution"
        VolunteerType.COMMUNITY_HELPER -> "Source of training (optional)"
        null -> "Organization / source"
    }

    val organizationPlaceholder = when (onboardingViewModel.volunteerType) {
        VolunteerType.VERIFIED_MEDICAL -> "Enter hospital, clinic, college, or institute"
        VolunteerType.COMMUNITY_HELPER -> "Enter school, NGO, workshop, or leave blank"
        null -> "Enter organization or source"
    }

    val summaryPlaceholder = when (onboardingViewModel.volunteerType) {
        VolunteerType.VERIFIED_MEDICAL ->
            "Example: I completed first aid training and have worked in emergency response."
        VolunteerType.COMMUNITY_HELPER ->
            "Example: I have basic first aid knowledge and can help with CPR awareness and support."
        null ->
            "Example: Share your background briefly."
    }

    Scaffold(
        containerColor = screenBg,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Volunteer setup",
                        color = Color.White,
                        fontSize = 22.sp,
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = title,
                            color = textPrimary,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = helperText,
                            color = textSecondary,
                            fontSize = 15.sp,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (onboardingViewModel.isVerifiedMedical()) {
                                        selectedBg
                                    } else {
                                        fieldBg
                                    },
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .padding(horizontal = 14.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = when (onboardingViewModel.volunteerType) {
                                    VolunteerType.VERIFIED_MEDICAL -> "Verified Medical Volunteer"
                                    VolunteerType.COMMUNITY_HELPER -> "Community Helper"
                                    null -> "Volunteer"
                                },
                                color = orangeStart,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Step 2 of 5",
                            color = hintColor,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                if (onboardingViewModel.isVerifiedMedical()) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Text(
                                text = "Experience level",
                                color = textPrimary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Required for verified medical volunteers.",
                                color = textSecondary,
                                fontSize = 13.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                onboardingViewModel.yearsOptions().forEach { level ->
                                    ExperienceChip(
                                        text = level.label,
                                        selected = onboardingViewModel.experienceLevel == level,
                                        onClick = { onboardingViewModel.selectExperienceLevel(level) },
                                        selectedColor = orangeStart,
                                        selectedBg = selectedBg,
                                        unselectedBg = fieldBg,
                                        borderColor = borderColor,
                                        textPrimary = textPrimary,
                                        textSecondary = textSecondary
                                    )
                                }
                            }

                            if (onboardingViewModel.experienceLevelError != null) {
                                Text(
                                    text = onboardingViewModel.experienceLevelError!!,
                                    color = Color.Red,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Text(
                            text = "Background category",
                            color = textPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Select the category that best describes your training.",
                            color = textSecondary,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            onboardingViewModel.backgroundOptions().forEach { category ->
                                ExperienceChip(
                                    text = category,
                                    selected = onboardingViewModel.backgroundCategory == category,
                                    onClick = { onboardingViewModel.selectBackgroundCategory(category) },
                                    selectedColor = orangeStart,
                                    selectedBg = selectedBg,
                                    unselectedBg = fieldBg,
                                    borderColor = borderColor,
                                    textPrimary = textPrimary,
                                    textSecondary = textSecondary
                                )
                            }
                        }

                        if (onboardingViewModel.backgroundCategoryError != null) {
                            Text(
                                text = onboardingViewModel.backgroundCategoryError!!,
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Text(
                            text = organizationLabel,
                            color = textPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = if (onboardingViewModel.isVerifiedMedical()) {
                                "Required for verification."
                            } else {
                                "Optional for community helpers."
                            },
                            color = textSecondary,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = onboardingViewModel.organization,
                            onValueChange = onboardingViewModel::onOrganizationChange,
                            placeholder = {
                                Text(
                                    text = organizationPlaceholder,
                                    color = hintColor
                                )
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(20.dp),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Words
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
                            isError = onboardingViewModel.organizationError != null,
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (onboardingViewModel.organizationError != null) {
                            Text(
                                text = onboardingViewModel.organizationError!!,
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Text(
                            text = "Tell us about your background",
                            color = textPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Keep it short and clear. Mention any relevant emergency or support experience.",
                            color = textSecondary,
                            fontSize = 13.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = onboardingViewModel.experienceSummary,
                            onValueChange = onboardingViewModel::onExperienceSummaryChange,
                            placeholder = {
                                Text(
                                    text = summaryPlaceholder,
                                    color = hintColor
                                )
                            },
                            minLines = 4,
                            maxLines = 6,
                            shape = RoundedCornerShape(20.dp),
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
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (onboardingViewModel.experienceSummaryError != null) {
                            Text(
                                text = onboardingViewModel.experienceSummaryError!!,
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 8.dp, start = 4.dp)
                            )
                        }
                    }
                }

                if (onboardingViewModel.isCommunityHelper()) {
                    Spacer(modifier = Modifier.height(14.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Checkbox(
                                checked = onboardingViewModel.safetyAgreed,
                                onCheckedChange = onboardingViewModel::onSafetyAgreedChange,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = orangeStart,
                                    uncheckedColor = borderColor
                                )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "I understand I should only provide help within my knowledge and training.",
                                color = textSecondary,
                                fontSize = 13.sp,
                                lineHeight = 19.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {
                        if (onboardingViewModel.validateExperienceStep()) {
                            onContinueClick()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = orangeStart
                    )
                ) {
                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun ExperienceChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    selectedColor: Color,
    selectedBg: Color,
    unselectedBg: Color,
    borderColor: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(999.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) selectedBg else unselectedBg
        ),
        border = if (selected) {
            androidx.compose.foundation.BorderStroke(1.5.dp, selectedColor)
        } else {
            androidx.compose.foundation.BorderStroke(1.dp, borderColor)
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = selectedColor,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }

            Text(
                text = text,
                color = if (selected) textPrimary else textSecondary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
