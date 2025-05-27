package com.personal.timer.ui.theme.views

import android.icu.util.TimeZone
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Global variable to store the selected time zone
var selectedTimeZone: TimeZone = TimeZone.getDefault() // Default to the system's time zone

@Composable
fun DisplayTextClock() {
    // Declares a mutable state variable `currentTime` to store the current time.
    // The state is remembered across recompositions and triggers UI updates when its value changes.
    var currentTime by remember { mutableStateOf(getCurrentTime(selectedTimeZone)) }
    // Mutable state to store the display name of the selected time zone
    var displayTimezone by remember { mutableStateOf(selectedTimeZone.displayName) }

    // LaunchedEffect to update the time and time zone display name every second
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = getCurrentTime(selectedTimeZone) // Update the current time
            displayTimezone = selectedTimeZone.displayName // Update the time zone display name
            delay(1000L) // 1 second
        }
    }

    // UI layout for displaying the clock
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the time zone name
        Text("$displayTimezone Clock",
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
            selectedTimeZone = TimeZone.getTimeZone(timeZoneId) // Update the global time zone variable
        }
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
        // Dropdown menu to display the list of time zones

    }
}

// Helper function to get the current time and return it as a formatted string
fun getCurrentTime(timeZone: TimeZone): String {
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault()) // Format for the time
    dateFormat.timeZone = java.util.TimeZone.getTimeZone(timeZone.id) // Set the selected time zone
    return dateFormat.format(Date()) // Return the formatted current time
}