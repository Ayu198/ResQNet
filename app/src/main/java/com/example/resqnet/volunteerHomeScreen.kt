package com.example.resqnet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun volunteerHomeScreen(
    navController: NavController,
    viewModel: VolunteerHomeViewModel = viewModel(),
    onViewAlertDetails: () -> Unit = {},
    onAcceptAlert: () -> Unit = {},
    onAlertsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val orangeStart = Color(0xFFFF7310)
    val orangeMid = Color(0xFFFF9B36)
    val orangeEnd = Color(0xFFFFB560)
    val screenBg = Color(0xFFFFF8F2)
    val cardBg = Color(0xFFFFFDFC)
    val softCard = Color(0xFFFFF5EA)
    val textPrimary = Color(0xFF2E241E)
    val textSecondary = Color(0xFF6B5D51)
    val mutedText = Color(0xFF84766A)
    val greenBg = Color(0xFFE9FFF0)
    val greenText = Color(0xFF177540)
    val darkAlertStart = Color(0xFF2B211C)
    val darkAlertEnd = Color(0xFF4A3528)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBg)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(404.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(orangeStart, orangeMid, orangeEnd)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = viewModel.volunteerRole,
                color = Color(0xFFFFF5EC),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = viewModel.titleMessage,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = viewModel.subtitleMessage,
                color = Color(0xFFFFF0E2),
                fontSize = 15.sp,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = Color.White.copy(alpha = 0.18f),
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                color = if (viewModel.isActive) Color(0xFF41D16C) else Color(0xFFFFD27A),
                                shape = CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = if (viewModel.isActive) "Active now" else "Not Active",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(containerColor = cardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(18.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(containerColor = softCard)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Today's status",
                                        color = textPrimary,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = viewModel.skillsSummary,
                                        color = textSecondary,
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = viewModel.statusMessage,
                                        color = textSecondary,
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                if (viewModel.isVerified) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = greenBg,
                                                shape = RoundedCornerShape(18.dp)
                                            )
                                            .padding(horizontal = 14.dp, vertical = 10.dp)
                                    ) {
                                        Text(
                                            text = "Verified",
                                            color = greenText,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Shield,
                                    contentDescription = null,
                                    tint = orangeStart,
                                    modifier = Modifier.size(20.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Available for nearby emergencies",
                                    color = textPrimary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.weight(1f),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Switch(
                                    checked = viewModel.isActive,
                                    onCheckedChange = viewModel::onToggleActive,
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = orangeStart,
                                        uncheckedThumbColor = Color.White,
                                        uncheckedTrackColor = Color(0xFFFFD9B8)
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    if (viewModel.hasActiveAlert) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(30.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Brush.linearGradient(
                                            colors = listOf(darkAlertStart, darkAlertEnd)
                                        ),
                                        shape = RoundedCornerShape(30.dp)
                                    )
                                    .padding(18.dp)
                            ) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.NotificationsActive,
                                            contentDescription = null,
                                            tint = Color(0xFFF4C297),
                                            modifier = Modifier.size(18.dp)
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Text(
                                            text = "Priority nearby alert",
                                            color = Color(0xFFF4C297),
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        text = viewModel.alertType,
                                        color = Color.White,
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = viewModel.alertLocation,
                                        color = Color(0xFFE8D7C8),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    )

                                    Spacer(modifier = Modifier.height(14.dp))

                                    Row {
                                        AlertMetaChip(text = viewModel.alertDistance)
                                        Spacer(modifier = Modifier.width(10.dp))
                                        AlertMetaChip(text = viewModel.alertPriority)
                                    }

                                    Spacer(modifier = Modifier.height(18.dp))

                                    Text(
                                        text = viewModel.alertSupportMessage,
                                        color = Color(0xFFF2E6DA),
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = viewModel.alertInstructionMessage,
                                        color = Color(0xFFF2E6DA),
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp
                                    )

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Button(
                                            onClick = onViewAlertDetails,
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(54.dp),
                                            shape = RoundedCornerShape(18.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.White.copy(alpha = 0.12f)
                                            )
                                        ) {
                                            Text(
                                                text = "View details",
                                                color = Color.White,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }

                                        Button(
                                            onClick = {
                                                onAcceptAlert()
                                                viewModel.markAlertAccepted()
                                            },
                                            modifier = Modifier
                                                .weight(1.15f)
                                                .height(54.dp),
                                            shape = RoundedCornerShape(18.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = orangeStart
                                            )
                                        ) {
                                            Text(
                                                text = "Accept response",
                                                color = Color.White,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(30.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8F2))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            ) {
                                Text(
                                    text = "No active alert right now",
                                    color = textPrimary,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Stay active to receive nearby emergency requests instantly.",
                                    color = mutedText,
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        MetricCard(
                            modifier = Modifier.weight(1f),
                            title = "Accepted today",
                            value = viewModel.acceptedToday,
                            valueColor = orangeStart
                        )

                        MetricCard(
                            modifier = Modifier.weight(1f),
                            title = "Response rating",
                            value = viewModel.responseRating,
                            valueColor = orangeStart
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8F2))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Text(
                                text = "Recent completed response",
                                color = textPrimary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = viewModel.recentResponseTitle,
                                color = textPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = viewModel.recentResponseSubtitle,
                                color = mutedText,
                                fontSize = 13.sp,
                                lineHeight = 19.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E7))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavItem(
                        label = "Dashboard",
                        selected = true,
                        onClick = {}
                    )
                    BottomNavItem(
                        label = "Alerts",
                        selected = false,
                        onClick = onAlertsClick
                    )
                    BottomNavItem(
                        label = "Profile",
                        selected = false,
                        onClick = onProfileClick
                    )
                }
            }
        }
    }
}

@Composable
private fun AlertMetaChip(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFFFFF5EC).copy(alpha = 0.14f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFFFFF5EC),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun MetricCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    valueColor: Color
) {
    val textPrimary = Color(0xFF2E241E)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF4E9))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Text(
                text = title,
                color = textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = value,
                color = valueColor,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = label,
        color = if (selected) Color(0xFFFF7A00) else Color(0xFF8E7E72),
        fontSize = 16.sp,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
        modifier = Modifier.clickable(onClick = onClick)
    )
}
