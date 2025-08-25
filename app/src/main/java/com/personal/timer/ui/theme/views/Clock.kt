package com.personal.timer.ui.theme.views

import android.icu.util.TimeZone
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.personal.timer.ui.theme.TimerTheme
import com.personal.timer.ui.theme.viewModel.ClockViewModel

@Composable
fun Clock(
    viewModel: ClockViewModel = ClockViewModel(),
) {
    val currentTime by viewModel.currentTime.collectAsStateWithLifecycle()
    val selectedTimeZone by viewModel.selectedTimeZone.collectAsStateWithLifecycle()

    // UI layout for displaying the clock
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the time zone name
        Text("${selectedTimeZone.id} Clock",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 24.sp,
        )
        // Display the current time
        Text(
            text = currentTime,
            color = Color.White,
            fontSize = 32.sp,
            modifier = Modifier.padding(5.dp)
        )
        // Dropdown menu for selecting a time zone
        TimeZoneDropdown { timeZoneId ->
            viewModel.updateTimeZone(timeZoneId) // Use the ViewModel's method to update time zone
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun TimeZoneDropdown(onTimeZoneSelected: (String) -> Unit) {
    // State to manage the dropdown menu's expanded state
    var expanded by remember { mutableStateOf(false) }
    // State to store the currently selected time zone
    var selectedTimeZone by remember { mutableStateOf("Select Time Zone") }
    // List of all available time zones
    val timeZones = TimeZone.getAvailableIDs()

    Column {
        // Button to open the dropdown menu
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { expanded = true } // Expand the dropdown menu when clicked
        ) {
            Text(text = selectedTimeZone) // Display the selected time zone
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false } // Collapse the menu when dismissed
            ) {
                timeZones.forEach { timeZone ->
                    DropdownMenuItem(
                        text = { Text(timeZone) }, // Display each time zone
                        onClick = {
                            selectedTimeZone = timeZone // Update the selected time zone
                            expanded = false  // Collapse the menu
                            onTimeZoneSelected(timeZone) // Notify the parent composable of the selection
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClockPreview() {
    TimerTheme {
        Clock()
    }
}