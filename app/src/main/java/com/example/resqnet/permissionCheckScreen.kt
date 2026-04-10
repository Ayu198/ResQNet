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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun permissionCheckScreen(
    navController: NavController,
    isLocationEnabled: Boolean,
    isNotificationEnabled: Boolean,
    onEnableLocationClick: () -> Unit,
    onEnableNotificationClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val orangeStart = Color(0xFFFF7A00)
    val orangeEnd = Color(0xFFFFA24C)
    val screenBg = Color(0xFFFFF8F2)
    val cardBg = Color(0xFFFFFCFA)
    val textPrimary = Color(0xFF2F241D)
    val textSecondary = Color(0xFF6E6258)
    val enabledBg = Color(0xFFE7F7EC)
    val enabledText = Color(0xFF1D7A3E)
    val disabledBg = Color(0xFFFFEFE2)
    val disabledText = Color(0xFFD56A00)

    val allReady = isLocationEnabled && isNotificationEnabled

    Scaffold(
        containerColor = screenBg
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
                    .height(320.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(orangeStart, orangeEnd)
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(top = 52.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.18f),
                            shape = RoundedCornerShape(24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(38.dp)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Enable essential access",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "ResQNet uses location and notifications to show nearby emergencies and alert you instantly.",
                    color = Color.White.copy(alpha = 0.92f),
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.88f)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        PermissionStatusCard(
                            icon = Icons.Default.LocationOn,
                            title = "Location access",
                            description = "Needed to detect nearby incidents and responders.",
                            isEnabled = isLocationEnabled,
                            enabledBg = enabledBg,
                            enabledText = enabledText,
                            disabledBg = disabledBg,
                            disabledText = disabledText,
                            buttonText = "Enable location",
                            onButtonClick = onEnableLocationClick
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        PermissionStatusCard(
                            icon = Icons.Default.Notifications,
                            title = "Notifications",
                            description = "Needed to receive emergency alerts in real time.",
                            isEnabled = isNotificationEnabled,
                            enabledBg = enabledBg,
                            enabledText = enabledText,
                            disabledBg = disabledBg,
                            disabledText = disabledText,
                            buttonText = "Enable notifications",
                            onButtonClick = onEnableNotificationClick
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onContinueClick,
                            enabled = allReady,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),
                            shape = RoundedCornerShape(22.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orangeStart,
                                disabledContainerColor = Color(0xFFFFD7B2)
                            )
                        ) {
                            Text(
                                text = "Continue",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun PermissionStatusCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    isEnabled: Boolean,
    enabledBg: Color,
    enabledText: Color,
    disabledBg: Color,
    disabledText: Color,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    val orangeStart = Color(0xFFFF7A00)
    val fieldBg = Color(0xFFFFF2E7)
    val textPrimary = Color(0xFF2F241D)
    val textSecondary = Color(0xFF6E6258)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = fieldBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = orangeStart
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = textPrimary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = description,
                        color = textSecondary,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Box(
                modifier = Modifier
                    .background(
                        color = if (isEnabled) enabledBg else disabledBg,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = if (isEnabled) "Enabled" else "Disabled",
                    color = if (isEnabled) enabledText else disabledText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            if (!isEnabled) {
                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = onButtonClick,
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = orangeStart
                    )
                ) {
                    Text(
                        text = buttonText,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
