package com.personal.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.personal.timer.ui.theme.Screens.ClockScreen
import com.personal.timer.ui.theme.Screens.StopwatchScreen
import com.personal.timer.ui.theme.Screens.TimerScreen
import com.personal.timer.ui.theme.TimerTheme
import com.personal.timer.ui.theme.viewModel.StopwatchViewModel
import com.personal.timer.ui.theme.viewModel.TimerViewModel

class MainActivity : ComponentActivity() {

    private val timerViewModel: TimerViewModel by viewModels()
    private val stopwatchViewModel: StopwatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerTheme {
                var currentScreen by remember { mutableStateOf(0) }

                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            actions = {
                                NavigationBarItem(
                                    icon = { Text("🕐") }, // Clock emoji
                                    label = { Text("Clock") },
                                    selected = currentScreen == 0,
                                    onClick = { currentScreen = 0 }
                                )
                                NavigationBarItem(
                                    icon = { Text("⏱️") }, // Timer emoji
                                    label = { Text("Timer") },
                                    selected = currentScreen == 1,
                                    onClick = { currentScreen = 1 }
                                )
                                NavigationBarItem(
                                    icon = {Text("⏲️") }, // Stopwatch emoji
                                    label = { Text("Stopwatch") },
                                    selected = currentScreen == 2,
                                    onClick = { currentScreen = 2 }
                                )
                            }
                        )
                    }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        when (currentScreen) {
                            0 -> ClockScreen()
                            1 -> TimerScreen(timerViewModel = timerViewModel)
                            2 -> StopwatchScreen(stopwatchViewModel)
                        }
                    }
                }
            }
        }
    }
}

