package com.esraa.dailymoodtracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esraa.dailymoodtracker.ui.theme.TextBlack

@Composable
fun HistoryScreen(entries: List<MoodEntry>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Weekly Mood History",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextBlack
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        entries.forEach { entry ->
            Text(
                text = "${entry.dayAndDate} : ${entry.moodText}",
                style = MaterialTheme.typography.bodyMedium,
                color = TextBlack,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}
