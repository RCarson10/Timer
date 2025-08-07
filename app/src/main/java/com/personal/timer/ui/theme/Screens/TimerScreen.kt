package com.personal.timer.ui.theme.Screens

import TimerPickerView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.personal.timer.ui.theme.viewModel.TimerViewModel
import java.util.Timer

// TODO: Update TimerScreen to accept TimerViewModel
@Composable
fun TimerScreen(timerViewModel: TimerViewModel) {
    var selectedTime by remember { mutableStateOf(Triple(0, 0, 0)) }
    
    // TODO: Collect timer state from ViewModel
    val timerState by timerViewModel.timerState.collectAsState()
    val remainingTime by timerViewModel.remainingTime.collectAsState()
    
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

        // TODO: Display timer state
        Text(
            text = "Timer State: ${timerState.name}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
        
        // TODO: Display remaining time
        if (remainingTime > 0) {
            val hours = remainingTime / (1000 * 60 * 60)
            val minutes = (remainingTime % (1000 * 60 * 60)) / (1000 * 60)
            val seconds = (remainingTime % (1000 * 60)) / 1000
            
            Text(
                text = "Remaining: ${hours}h ${minutes}m ${seconds}s",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // TODO: Display selected time
        Text(
            text = "Selected: ${selectedTime.first}h ${selectedTime.second}m ${selectedTime.third}s",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 24.dp)
        )

        // TODO: Add timer control buttons
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // TODO: Start timer button
            Button(
                onClick = {
                    // TODO: Convert selected time to milliseconds
                    val durationMs = (selectedTime.first * 60 * 60 * 1000L) +
                                   (selectedTime.second * 60 * 1000L) +
                                   (selectedTime.third * 1000L)
                    
                    // TODO: Start timer using ViewModel
                    timerViewModel.startTimer(durationMs)
                },
                enabled = timerState == TimerViewModel.TimerState.IDLE || 
                         timerState == TimerViewModel.TimerState.STOPPED ||
                         timerState == TimerViewModel.TimerState.COMPLETED
            ) {
                Text("Start Timer")
            }
            
            // TODO: Stop timer button
            Button(
                onClick = {
                    // TODO: Stop timer using ViewModel
                    timerViewModel.stopTimer()
                },
                enabled = timerState == TimerViewModel.TimerState.RUNNING
            ) {
                Text("Stop Timer")
            }
            
            // TODO: Reset timer button
            Button(
                onClick = {
                    // TODO: Reset timer using ViewModel
                    timerViewModel.resetTimer()
                },
                enabled = timerState != TimerViewModel.TimerState.IDLE
            ) {
                Text("Reset Timer")
            }
        }
        
        // TODO: Add pause/resume buttons (optional)
        if (timerState == TimerViewModel.TimerState.RUNNING || 
            timerState == TimerViewModel.TimerState.PAUSED) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        // TODO: Pause timer using ViewModel
                        timerViewModel.pauseTimer()
                    },
                    enabled = timerState == TimerViewModel.TimerState.RUNNING
                ) {
                    Text("Pause")
                }
                
                Button(
                    onClick = {
                        // TODO: Resume timer using ViewModel
                        timerViewModel.resumeTimer()
                    },
                    enabled = timerState == TimerViewModel.TimerState.PAUSED
                ) {
                    Text("Resume")
                }
            }
        }
    }
}