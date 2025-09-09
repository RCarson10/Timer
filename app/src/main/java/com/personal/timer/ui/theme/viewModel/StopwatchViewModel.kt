package com.personal.timer.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personal.timer.data.LapTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopwatchViewModel @Inject constructor(): ViewModel() {
    private val _runningTime = MutableStateFlow(0L)
    val runningTime: StateFlow<Long> = _runningTime.asStateFlow()
    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()
    val laps = mutableListOf<LapTime>()

    fun startStopwatch() {
        _isRunning.value = true
        viewModelScope.launch {
            while (_isRunning.value) {
                delay(1)
                _runningTime.value += 1
            }
        }
    }
    fun stopStopwatch() {
        _isRunning.value = false
        laps.add(
            LapTime(
                laps.size + 1,
                _runningTime.value,
                if (laps.isEmpty()) _runningTime.value
                else _runningTime.value - laps.last().time)
        )
        viewModelScope.cancel()
    }
    fun resetStopwatch() {
        _runningTime.value = 0L
        laps.clear()
    }
    fun recordLap() {
        if (_isRunning.value) {
            laps.add(
                LapTime(
                laps.size + 1,
                _runningTime.value,
                if (laps.isEmpty()) _runningTime.value
                else _runningTime.value - laps.last().time)
            )
        }
    }


}