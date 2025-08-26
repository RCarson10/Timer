package com.personal.timer.ui.theme.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.personal.timer.ui.theme.viewModel.StopwatchViewModel

@Composable
fun Stopwatch(viewModel: StopwatchViewModel) {

    val stopwatchTime by viewModel.runningTime.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val hasLaps = viewModel.laps.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text("Stopwatch", color = Color.White)
        Text(
            text = formatTime(stopwatchTime),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Row {
            Button(onClick = {
                if (isRunning) viewModel.stopStopwatch()
                else viewModel.startStopwatch()
            }) {
                if (isRunning) Text("Stop")
                else Text("Start")
            }
            Button(onClick = {
                if (isRunning) viewModel.recordLap()
                else viewModel.resetStopwatch()
            },
                enabled = isRunning || hasLaps
            )
            {
                if (isRunning) Text("Lap")
                else Text("Reset")
            }
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

@Preview
@Composable
fun StopwatchPreview() {
    Stopwatch(viewModel = StopwatchViewModel())
}