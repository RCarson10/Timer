package com.personal.timer.ui.theme.views

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
            text = stopwatchTime.toString(),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Row {
            Button(onClick = { /* Start stopwatch logic */ }) {
                Text("Start")
            }
            Button(onClick = { /* Reset stopwatch logic */ }) {
                Text("Lap")
            }
        }
    }
}

@Preview
@Composable
fun StopwatchPreview() {
    Stopwatch(viewModel = StopwatchViewModel())
}