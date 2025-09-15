package com.personal.timer.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personal.timer.data.LapTime
import com.personal.timer.repository.StopwatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopwatchViewModel @Inject constructor(
    private val repository: StopwatchRepository
): ViewModel() {
    private val _runningTime = MutableStateFlow(0L)
    val runningTime: StateFlow<Long> = _runningTime.asStateFlow()
    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _laps = MutableStateFlow<List<LapTime>>(emptyList())
    val laps: StateFlow<List<LapTime>> = _laps.asStateFlow()

    fun startStopwatch() {
        if ( !_isRunning.value && _runningTime.value == 0L) {
            viewModelScope.launch {
                repository.startNewSession()
                _laps.value = emptyList()
            }
        }
        _isRunning.value = true
        viewModelScope.launch {
            while (_isRunning.value) {
                delay(10)
                _runningTime.value += 10
            }
        }
    }
    fun stopStopwatch() {
        _isRunning.value = false
        viewModelScope.launch {
           val newLap = LapTime(
                laps.value.size + 1,
                _runningTime.value,
                if (laps.value.isEmpty()) _runningTime.value
                else _runningTime.value - laps.value.last().time
           )
            repository.saveLap(newLap)
            _laps.value = repository.getLapsForCurrentSession()
        }
    }

    fun resetStopwatch() {
        _runningTime.value = 0L
        viewModelScope.launch {
            repository.clearCurrentSession()
            _laps.value = emptyList()
        }
    }

    fun recordLap() {
        if (_isRunning.value) {
            viewModelScope.launch {
                val newLap = LapTime(
                    laps.value.size + 1,
                    _runningTime.value,
                    if (laps.value.isEmpty()) _runningTime.value
                    else _runningTime.value - laps.value.last().time
                )
                repository.saveLap(newLap)
                _laps.value = repository.getLapsForCurrentSession()
            }
        }
    }
}