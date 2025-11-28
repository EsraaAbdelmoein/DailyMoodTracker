package com.esraa.dailymoodtracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Simple text styles with a bit bigger size
val Typography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp
    )
)
