package com.personal.timer.ui.theme.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.personal.timer.ui.theme.viewModel.ClockViewModel
import com.personal.timer.ui.theme.views.Clock

@Composable
fun ClockScreen(
    viewModel: ClockViewModel = hiltViewModel()
) {
    val currentTime by viewModel.currentTime.collectAsStateWithLifecycle()
    val selectedTimeZone by viewModel.selectedTimeZone.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Clock(
            currentTime = currentTime,
            selectedTimeZone = selectedTimeZone,
            onTimeZoneSelected = { timeZoneId ->
                viewModel.updateTimeZone(timeZoneId)
            }
        )
    }
}