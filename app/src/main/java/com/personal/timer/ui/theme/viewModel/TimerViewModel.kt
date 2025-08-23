package com.personal.timer.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class TimerState {
    RUNNING,
    PAUSED,
    STOPPED,
    COMPLETED
}

class TimerViewModel : ViewModel() {
    private val _timerState = MutableStateFlow(TimerState.STOPPED)
    private val _remainingTime = MutableStateFlow(0L)
    val timerState: StateFlow<TimerState> = _timerState.asStateFlow()
    val remainingTime: StateFlow<Long> = _remainingTime.asStateFlow()


    fun startTimer(duration: Long) {
        _remainingTime.value = duration
        _timerState.value = TimerState.RUNNING
    }

    fun stopTimer() {
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
    }

    fun resumeTimer(remainingTime: Long) {
        if (_timerState.value == TimerState.PAUSED) {
            _timerState.value = TimerState.RUNNING
            _remainingTime.value = remainingTime
            startTimer(remainingTime)
        }
    }

}

