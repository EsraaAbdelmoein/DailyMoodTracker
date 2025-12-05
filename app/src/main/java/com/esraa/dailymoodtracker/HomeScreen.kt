package com.esraa.dailymoodtracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esraa.dailymoodtracker.ui.theme.ButtonBlue
import com.esraa.dailymoodtracker.ui.theme.TextBlack
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Simple data for one line in the history
data class MoodEntry(
    val dayAndDate: String,
    val moodText: String
)

@Composable
fun HomeScreen() {

    // What mood button is selected
    var selectedMood by remember { mutableStateOf("") }

    // Saved mood for today
    var savedMood by remember { mutableStateOf("") }

    // Short note for today
    var noteText by remember { mutableStateOf("") }
    var savedNote by remember { mutableStateOf("") }

    // Show or hide the 7-day history
    var showHistory by remember { mutableStateOf(true) }

    // List for the 7-day history
    var historyEntries by remember { mutableStateOf(buildWeekEntries("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            text = "Daily Mood Tracker",
            style = MaterialTheme.typography.titleLarge,
            color = TextBlack
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Small question
        Text(
            text = "How do you feel today?",
            style = MaterialTheme.typography.bodyMedium,
            color = TextBlack
        )

        Spacer(modifier = Modifier.height(24.dp))

        // First row of moods
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MoodButton(label = "Calm", emoji = "😌") {
                selectedMood = "Calm"
            }
            MoodButton(label = "Happy", emoji = "😊") {
                selectedMood = "Happy"
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Second row of moods
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MoodButton(label = "Sad", emoji = "😔") {
                selectedMood = "Sad"
            }
            MoodButton(label = "Angry", emoji = "😠") {
                selectedMood = "Angry"
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Third row with one button in the middle
        MoodButton(label = "Tired", emoji = "🥱") {
            selectedMood = "Tired"
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Small text to show which mood is selected now
        Text(
            text = if (selectedMood.isNotEmpty())
                "Selected mood: $selectedMood"
            else
                "Selected mood: none",
            style = MaterialTheme.typography.bodyMedium,
            color = TextBlack
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Note input for today
        OutlinedTextField(
            value = noteText,
            onValueChange = { noteText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Short note for today") },
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save button for today
        Button(
            onClick = {
                savedMood = selectedMood
                savedNote = noteText
                historyEntries = buildWeekEntries(savedMood)
            },
            enabled = selectedMood.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBlue,
                contentColor = TextBlack
            )
        ) {
            Text(text = "Save today")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Show saved mood and note
        if (savedMood.isNotEmpty()) {
            Text(
                text = "Saved mood: $savedMood",
                style = MaterialTheme.typography.bodyMedium,
                color = TextBlack
            )

            if (savedNote.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Today note: $savedNote",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextBlack
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Simple tip text based on mood
            val tipText = getTipForMood(savedMood)
            if (tipText.isNotEmpty()) {
                Text(
                    text = "Tip: $tipText",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextBlack
                )
            }
        } else {
            Text(
                text = "No mood saved yet.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextBlack
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Button to hide / show history
        Button(
            onClick = { showHistory = !showHistory },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBlue,
                contentColor = TextBlack
            )
        ) {
            Text(
                text = if (showHistory) "Hide 7-Day History" else "Show 7-Day History"
            )
        }

        // 7-day history list
        if (showHistory) {
            HistoryScreen(entries = historyEntries)
        }
    }
}

// One mood button
@Composable
fun MoodButton(label: String, emoji: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonBlue,
            contentColor = TextBlack
        ),
        modifier = Modifier
            .width(140.dp)
            .height(44.dp)
    ) {
        Text(
            text = "$emoji  $label",
            fontSize = 14.sp
        )
    }
}

// Build 7 days: today and previous 6 days
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

// Simple tips for each mood
fun getTipForMood(mood: String): String {
    return when (mood) {
        "Calm" -> "Keep a small quiet moment for yourself today."
        "Happy" -> "Share this good feeling with someone you love."
        "Sad" -> "Talk to someone you trust and be kind to yourself."
        "Angry" -> "Take a few deep breaths before you react."
        "Tired" -> "Try to rest, drink water, and sleep a bit earlier."
        else -> ""
    }
}
