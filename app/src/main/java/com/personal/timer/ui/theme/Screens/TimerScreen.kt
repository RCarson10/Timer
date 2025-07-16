package com.personal.timer.ui.theme.Screens

import TimerPickerView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Timer

// Example usage
@Composable
fun TimerScreen() {
    var selectedTime by remember { mutableStateOf(Triple(0, 0, 0)) }

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

        Text(
            text = "Selected: ${selectedTime.first}h ${selectedTime.second}m ${selectedTime.third}s",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 24.dp)
        )

        Button(
            onClick = {

            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Start Timer")
        }
    }
}