package com.personal.timer.ui.theme.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.personal.timer.data.LapTime
import com.personal.timer.ui.theme.viewModel.StopwatchViewModel

@Composable
fun Stopwatch(viewModel: StopwatchViewModel) {
    val stopwatchTime by viewModel.runningTime.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val laps by viewModel.laps.collectAsState()
    val hasLaps = laps.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Stopwatch", color = Color.White)
        
        // Enhanced timer display
        StopwatchDisplay(
            time = stopwatchTime,
            isRunning = isRunning
        )
        
        // Control buttons
        StopwatchControlButtons(
            isRunning = isRunning,
            hasLaps = hasLaps,
            onStart = { viewModel.startStopwatch() },
            onStop = { viewModel.stopStopwatch() },
            onLap = { viewModel.recordLap() },
            onReset = { viewModel.resetStopwatch() }
        )

        // Enhanced laps display
        if (hasLaps) {
            LapsDisplay(laps = laps)
        }
    }
}

@SuppressLint("DefaultLocale")
private fun formatTime(timeInMillis: Long): String {
    val minutes = ((timeInMillis % (1000 * 60 * 60)) / (1000 * 60)).toInt()
    val seconds = ((timeInMillis % (1000 * 60)) / 1000).toInt()
    val milliseconds = (timeInMillis % 1000).toInt() // Get full milliseconds

    return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds)
}

@Composable
fun StopwatchDisplay(
    time: Long,
    isRunning: Boolean
) {
    val color = if (isRunning) Color.Green else Color.White
    
    Text(
        text = formatTime(time),
        style = MaterialTheme.typography.displayLarge,
        color = color
    )
}

@Composable
fun StopwatchControlButtons(
    isRunning: Boolean,
    hasLaps: Boolean,
    onStart: () -> Unit,
    onStop: () -> Unit,
    onLap: () -> Unit,
    onReset: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {
                if (isRunning) onStop() else onStart()
            }
        ) {
            Text(if (isRunning) "Stop" else "Start")
        }
        
        Button(
            onClick = {
                if (isRunning) onLap() else onReset()
            },
            enabled = isRunning || hasLaps
        ) {
            Text(if (isRunning) "Lap" else "Reset")
        }
    }
}

@Composable
fun LapsDisplay(laps: List<LapTime>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Laps:",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            items(laps) { lap ->
                LapItem(lap = lap)
            }
        }
    }
}

@Composable
fun LapItem(lap: LapTime) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Lap ${lap.lapNumber}",
            color = Color.White
        )
        Text(
            text = "Split: ${formatTime(lap.splitTime)}",
            color = Color.White
        )
        Text(
            text = "Total: ${formatTime(lap.time)}",
            color = Color.White
        )
    }
}
