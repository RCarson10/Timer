package com.personal.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.personal.timer.ui.theme.Screens.ClockScreen
import com.personal.timer.ui.theme.Screens.TimerScreen
import com.personal.timer.ui.theme.TimerTheme
import com.personal.timer.ui.theme.viewModel.TimerViewModel

class MainActivity : ComponentActivity() {
    
    // TODO: Add ViewModel instance
    private val timerViewModel: TimerViewModel by viewModels()
    
    // TODO: Add local broadcast receiver for timer completion
    private val timerCompletionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // TODO: Handle timer completion
            if (intent?.action == "TIMER_COMPLETED") {
                handleTimerCompletion()
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
//        // TODO: Initialize AlarmManager in ViewModel
//        timerViewModel.initializeAlarmManager(this)
        
        // TODO: Register local broadcast receiver
//        LocalBroadcastManager.getInstance(this).registerReceiver(
//            timerCompletionReceiver,
//            IntentFilter("TIMER_COMPLETED")
//        )
        
        setContent {
            TimerTheme {
                var currentScreen by remember { mutableStateOf(0) }

                Column {
                    // Tab buttons
                    Row {
                        Button(onClick = { currentScreen = 0 }) { Text("Clock") }
                        Button(onClick = { currentScreen = 1 }) { Text("Timer") }
                    }

                    // Show only one screen at a time
                    when (currentScreen) {
                        0 -> ClockScreen()
                        1 -> TimerScreen(timerViewModel = timerViewModel)
                    }
                }
            }
        }
    }
    
    // TODO: Handle timer completion
    private fun handleTimerCompletion() {
        // TODO: Update ViewModel state
//        timerViewModel.onTimerComplete()
        
        // TODO: Show additional UI feedback if needed
        // For example, play sound, show dialog, etc.
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // TODO: Unregister broadcast receiver
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(timerCompletionReceiver)
    }
}

