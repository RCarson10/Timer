package com.personal.timer.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.icu.util.TimeZone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ClockViewModel : ViewModel() {
    // StateFlow for the current time
    private val _currentTime = MutableStateFlow("")
    val currentTime: StateFlow<String> = _currentTime.asStateFlow()

    // StateFlow for the selected time zone
    private val _selectedTimeZone = MutableStateFlow(TimeZone.getDefault())
    val selectedTimeZone: StateFlow<TimeZone> = _selectedTimeZone.asStateFlow()

    init {
        // Initialize with current time
        updateTime()
        // Start updating the time every second
        viewModelScope.launch {
            while (true) {
                delay(1000L) // 1 second
                updateTime()
            }
        }
    }

    // Updates the current time based on the selected time zone
    private fun updateTime() {
        val timeZone = _selectedTimeZone.value
        val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        dateFormat.timeZone = java.util.TimeZone.getTimeZone(timeZone.id)
        _currentTime.value = dateFormat.format(Date())
    }

    // Updates the selected time zone
    fun updateTimeZone(timeZoneId: String) {
        _selectedTimeZone.value = TimeZone.getTimeZone(timeZoneId)
    }
}