package com.personal.timer

import android.os.Bundle
import android.widget.TextClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.personal.timer.ui.theme.TimerTheme

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

@Composable
fun DisplayTextClock() {
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
        // Android view composable to use xml views in compose
        AndroidView(
            factory = { context ->

                // Text Clock
                TextClock(context).apply {

                    // set hour format
                    format12Hour = "hh:mm a"

                    // set time zone
                    timeZone = timeZone

                    // set text size
                    textSize= 32f

                    // set text color
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                }
            },
            modifier = Modifier.padding(5.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ClockPreview() {
    TimerTheme {
        DisplayTextClock()
    }
}