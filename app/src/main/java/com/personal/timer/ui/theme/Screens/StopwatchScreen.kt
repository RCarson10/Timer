package com.personal.timer.ui.theme.Screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.personal.timer.ui.theme.viewModel.StopwatchViewModel
import com.personal.timer.ui.theme.views.Stopwatch

@Composable
fun StopwatchScreen(viewModel: StopwatchViewModel = hiltViewModel()) {
    Stopwatch(viewModel)
}