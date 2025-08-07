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
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.localbroadcastmanager.content.LocalBroadcastManager
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
        
        // TODO: Initialize AlarmManager in ViewModel
        timerViewModel.initializeAlarmManager(this)
        
        // TODO: Register local broadcast receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
            timerCompletionReceiver,
            IntentFilter("TIMER_COMPLETED")
        )
        
        setContent {
            TimerTheme {
                ClockScreen()
                // TODO: Pass ViewModel to TimerScreen
                TimerScreen(timerViewModel = timerViewModel)
            }
        }
    }
    
    // TODO: Handle timer completion
    private fun handleTimerCompletion() {
        // TODO: Update ViewModel state
        timerViewModel.onTimerComplete()
        
        // TODO: Show additional UI feedback if needed
        // For example, play sound, show dialog, etc.
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // TODO: Unregister broadcast receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(timerCompletionReceiver)
    }
}

