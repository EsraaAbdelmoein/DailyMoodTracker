package com.esraa.dailymoodtracker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen() {

    // I keep the last selected mood here
    var currentMood by remember { mutableStateOf("") }

    // I use this flag to show or hide the history
    var showHistory by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title
            Text(
                text = "Daily Mood Tracker",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Small question text
            Text(
                text = "How do you feel today?",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            // First row of moods
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoodButton(
                    label = "Calm",
                    emoji = "😌"
                ) { currentMood = "Calm" }

                MoodButton(
                    label = "Happy",
                    emoji = "😊"
                ) { currentMood = "Happy" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Second row of moods
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoodButton(
                    label = "Sad",
                    emoji = "😢"
                ) { currentMood = "Sad" }

                MoodButton(
                    label = "Angry",
                    emoji = "😡"
                ) { currentMood = "Angry" }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Third row
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoodButton(
                    label = "Tired",
                    emoji = "🥱"
                ) { currentMood = "Tired" }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Text for the saved mood
            Text(
                text = if (currentMood.isNotEmpty()) "Saved mood: $currentMood" else "No mood saved yet",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Button to show / hide history
            Button(
                onClick = { showHistory = !showHistory },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (showHistory) "Hide 7-Day History" else "Show 7-Day History",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // I rebuild the 7-day list every time the current mood changes
            if (showHistory) {
                val weekEntries = remember(currentMood) { buildWeekEntries(currentMood) }

                Spacer(modifier = Modifier.height(24.dp))

                HistorySection(entries = weekEntries)
            }
        }
    }
}

// Simple button for one mood
@Composable
fun MoodButton(
    label: String,
    emoji: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .width(140.dp)
            .height(46.dp)
    ) {
        Text(
            text = "$emoji  $label",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 14.sp
        )
    }
}

// I build 7 days: today + previous 6 days here
fun buildWeekEntries(currentMood: String): List<MoodEntry> {
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE - MMM dd")

    return (0..6).map { offset ->
        val date = today.minusDays(offset.toLong())
        val label = date.format(formatter)

        val moodText = if (offset == 0 && currentMood.isNotEmpty()) {
            currentMood
        } else {
            "No mood saved"
        }

        MoodEntry(
            dayAndDate = label,
            moodText = moodText
        )
    }
}
