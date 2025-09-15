package com.personal.timer.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class TimerState {
    RUNNING,
    PAUSED,
    STOPPED,
    COMPLETED
}

@HiltViewModel
class TimerViewModel @Inject constructor() : ViewModel() {
    private val _timerState = MutableStateFlow(TimerState.STOPPED)
    private val _remainingTime = MutableStateFlow(0L)
    val timerState: StateFlow<TimerState> = _timerState.asStateFlow()
    val remainingTime: StateFlow<Long> = _remainingTime.asStateFlow()

    private val _showToast = MutableStateFlow(false)
    val showToast: StateFlow<Boolean> = _showToast.asStateFlow()
    private var timerJob: Job? = null


     fun startTimer(duration: Long) {
        _remainingTime.value = duration
        _timerState.value = TimerState.RUNNING
         timerJob?.cancel()

         timerJob = viewModelScope.launch {
             var remaining = duration
             _remainingTime.value = remaining
             _timerState.value = TimerState.RUNNING

             while (remaining > 0 && _timerState.value == TimerState.RUNNING) {
                 delay(1000)
                 remaining -= 1000
                 _remainingTime.value = remaining
             }

             if (remaining <= 0) {
                 _timerState.value = TimerState.COMPLETED
                 _remainingTime.value = 0L
                 _showToast.value = true // Trigger toast
             }
         }
    }

    fun stopTimer() {
        timerJob?.cancel() // Cancel the running coroutine
        _timerState.value = TimerState.STOPPED
        _remainingTime.value = 0L
    }

    fun resetTimer(duration: Long) {
        _timerState.value = TimerState.STOPPED
        _remainingTime.value = 0L
        startTimer(duration)
    }

    fun pauseTimer() {
        if (_timerState.value == TimerState.RUNNING) {
            _timerState.value = TimerState.PAUSED
        }
        timerJob?.cancel()
    }

    fun resumeTimer() {
        if (_timerState.value == TimerState.PAUSED) {
            _timerState.value = TimerState.RUNNING
        }
    }

    fun onToastShown() {
        _showToast.value = false
    }

}

