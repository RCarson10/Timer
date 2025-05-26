package com.personal.timer.ui.theme.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

@Composable
fun DisplayTextClock() {
    // Declares a mutable state variable `currentTime` to store the current time.
    // The state is remembered across recompositions and triggers UI updates when its value changes.
    var currentTime by remember { mutableStateOf(getCurrentTime()) }

    // Update the time every second
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = getCurrentTime()
            delay(1000L) // 1 second
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Local Timezone Clock",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 24.sp,
        )
        Text(
            text = currentTime,
            color = Color.White,
            fontSize = 32.sp,
            modifier = Modifier.padding(5.dp)
        )
    }
}

// Helper function to get the current time and return it as a formatted string
fun getCurrentTime(): String {
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return dateFormat.format(Date())
}