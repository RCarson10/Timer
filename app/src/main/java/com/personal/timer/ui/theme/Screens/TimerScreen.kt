package com.personal.timer.ui.theme.Screens

import TimerPickerView
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.personal.timer.ui.theme.viewModel.TimerState
import com.personal.timer.ui.theme.viewModel.TimerViewModel

@Composable
fun TimerScreen(timerViewModel: TimerViewModel = hiltViewModel()) {
    var selectedTime by remember { mutableStateOf(Triple(0, 0, 0)) }
    val context = LocalContext.current

    val timerState by timerViewModel.timerState.collectAsState()
    val remainingTime by timerViewModel.remainingTime.collectAsState()
    val showToast by timerViewModel.showToast.collectAsState()

    LaunchedEffect(showToast) {
        if (showToast) {
            // Show your toast here
            Toast.makeText(context, "Timer completed!", Toast.LENGTH_SHORT).show()
            timerViewModel.onToastShown()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Select Timer Duration",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TimerPickerView(
            initialHours = selectedTime.first,
            initialMinutes = selectedTime.second,
            initialSeconds = selectedTime.third,
            onTimeChanged = { hours, minutes, seconds ->
                selectedTime = Triple(hours, minutes, seconds)
            }
        )

        if (remainingTime > 0) {
            TimerDisplay(
                remainingTime = remainingTime,
                state = timerState,
                initialTime = (selectedTime.first * 60 * 60 * 1000L) +
                             (selectedTime.second * 60 * 1000L) +
                             (selectedTime.third * 1000L)
            )
        }
        TimerControlButtons(
            timerState = timerState,
            selectedTime = selectedTime,
            onStart = { durationMs ->
                timerViewModel.startTimer(durationMs)
            },
            onStop = {
                timerViewModel.stopTimer()
            },
            onReset = { durationMs ->
                timerViewModel.resetTimer(durationMs)
            },
            onPause = {
                timerViewModel.pauseTimer()
            },
            onResume = {
                timerViewModel.resumeTimer()
            }
        )
    }
}

@Composable
fun TimerDisplay(
    remainingTime: Long,
    state: TimerState,
    initialTime: Long
) {
    val hours = remainingTime / (1000 * 60 * 60)
    val minutes = (remainingTime % (1000 * 60 * 60)) / (1000 * 60)
    val seconds = (remainingTime % (1000 * 60)) / 1000
    
    val color = when (state) {
        TimerState.RUNNING -> Color.Green
        TimerState.PAUSED -> Color.Blue
        TimerState.COMPLETED -> Color.Red
        TimerState.STOPPED -> Color.Gray
    }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        // Progress bar
        LinearProgressIndicator(
            progress = { 
                if (initialTime > 0) {
                    (initialTime - remainingTime).toFloat() / initialTime.toFloat()
                } else 0f
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        
        // Timer display
        Text(
            text = "Remaining: ${hours}h ${minutes}m ${seconds}s",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 36.sp
            ),
            color = color
        )
        
        // State indicator
        Text(
            text = when (state) {
                TimerState.RUNNING -> "Running"
                TimerState.PAUSED -> "Paused"
                TimerState.COMPLETED -> "Completed!"
                TimerState.STOPPED -> "Stopped"
            },
            style = MaterialTheme.typography.bodyMedium,
            color = color
        )
    }
}

@Composable
fun TimerControlButtons(
    timerState: TimerState,
    selectedTime: Triple<Int, Int, Int>,
    onStart: (Long) -> Unit,
    onStop: () -> Unit,
    onReset: (Long) -> Unit,
    onPause: () -> Unit,
    onResume: () -> Unit
) {
    val durationMs = (selectedTime.first * 60 * 60 * 1000L) +
                    (selectedTime.second * 60 * 1000L) +
                    (selectedTime.third * 1000L)
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        // Main control buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onStart(durationMs) },
                enabled = timerState == TimerState.STOPPED || timerState == TimerState.COMPLETED
            ) {
                Text("Start")
            }
            
            Button(
                onClick = { onStop() },
                enabled = timerState == TimerState.RUNNING || timerState == TimerState.PAUSED
            ) {
                Text("Stop")
            }

            Button(
                onClick = { onReset(durationMs) },
                enabled = timerState != TimerState.STOPPED
            ) {
                Text("Reset")
            }
        }
        
        // Pause/Resume buttons (only show when timer is running or paused)
        if (timerState == TimerState.RUNNING || timerState == TimerState.PAUSED) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onPause,
                    enabled = timerState == TimerState.RUNNING
                ) {
                    Text("Pause")
                }
                
                Button(
                    onClick = onResume,
                    enabled = timerState == TimerState.PAUSED
                ) {
                    Text("Resume")
                }
            }
        }
    }
}