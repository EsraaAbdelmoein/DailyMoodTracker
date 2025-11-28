package com.esraa.dailymoodtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.esraa.dailymoodtracker.ui.theme.DailyMoodTrackerTheme

// This activity is the starting point of the app
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // I wrap my UI with the app theme
            DailyMoodTrackerTheme {
                HomeScreen()
            }
        }
    }
}
