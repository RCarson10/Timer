package com.personal.timer.ui.theme.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.personal.timer.ui.theme.viewModel.ClockViewModel
import com.personal.timer.ui.theme.views.Clock

@Composable
fun ClockScreen(
    viewModel: ClockViewModel = ClockViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the Clock composable
        Clock(viewModel)
    }
}