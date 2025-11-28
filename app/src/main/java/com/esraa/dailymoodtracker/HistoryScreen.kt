package com.esraa.dailymoodtracker

import androidx.compose.foundation.layout.Column
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

// Each item in the history has a day/date and the mood text
data class MoodEntry(
    val dayAndDate: String,
    val moodText: String
)

// This part shows the weekly mood history lines
@Composable
fun HistorySection(entries: List<MoodEntry>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Weekly Mood History",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        // I add a little space under the title
        Text(
            text = "",
            fontSize = 4.sp
        )

        entries.forEach { entry ->
            Text(
                text = "${entry.dayAndDate} : ${entry.moodText}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}
