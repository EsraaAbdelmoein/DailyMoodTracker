package com.esraa.dailymoodtracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Here I connect my custom colors to the Material theme
private val LightColors = lightColorScheme(
    primary = ButtonBlue,
    onPrimary = Color.Black,
    background = ScreenPurple,
    onBackground = TextBlack,
    surface = ScreenPurple,
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
