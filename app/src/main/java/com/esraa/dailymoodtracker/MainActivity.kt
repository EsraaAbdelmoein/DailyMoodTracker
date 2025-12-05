package com.esraa.dailymoodtracker

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.esraa.dailymoodtracker.ui.theme.DailyMoodTrackerTheme

class MainActivity : ComponentActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create simple background music
        mediaPlayer = MediaPlayer.create(this, R.raw.relaxing_music)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(0.4f, 0.4f)
        mediaPlayer.start()

        // Show the Compose UI
        setContent {
            DailyMoodTrackerTheme {
                HomeScreen()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (this::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (this::mediaPlayer.isInitialized && !mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}
