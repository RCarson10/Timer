package com.personal.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.personal.timer.ui.theme.TimerTheme
import com.personal.timer.ui.theme.views.DisplayTextClock

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerTheme {
                DisplayTextClock()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClockPreview() {
    TimerTheme {
        DisplayTextClock()
    }
}