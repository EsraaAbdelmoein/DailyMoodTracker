package com.esraa.dailymoodtracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Connect our colors to Material theme
private val LightColors = lightColorScheme(
    primary = ButtonBlue,
    secondary = ButtonBlue,
    background = ScreenPurple,
    surface = ScreenPurple,
    onPrimary = TextBlack,
    onBackground = TextBlack,
    onSurface = TextBlack
)

@Composable
fun DailyMoodTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
