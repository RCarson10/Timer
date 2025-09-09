package com.personal.timer.ui.theme.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.icu.util.TimeZone
import com.personal.timer.network.WorldTimeResponse
import com.personal.timer.repository.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ClockViewModel @Inject constructor(
    private val repository: TimerRepository
): ViewModel() {
    // StateFlow for the current time
    private val _currentTime = MutableStateFlow("")
    val currentTime: StateFlow<String> = _currentTime.asStateFlow()

    // StateFlow for the selected time zone
    private val _selectedTimeZone = MutableStateFlow(TimeZone.getDefault())
    val selectedTimeZone: StateFlow<TimeZone> = _selectedTimeZone.asStateFlow()

    init {
        // Initialize with local time first
        updateTime()
        
        // Start updating the time every second
        viewModelScope.launch {
            // Wait a bit before first API call to ensure app is fully loaded
            delay(3000L)
            while (true) {
                try {
                    fetchTimeFromAPI()
                } catch (e: Exception) {
                    // If API fails, just use local time
                    updateTime()
                }
                delay(10000L) // 1 second
            }
        }
    }

    // Fetches time from remote API and updates the UI
    private suspend fun fetchTimeFromAPI() {
        try {
            val response = repository.getTime(_selectedTimeZone.value.id)
            // Parse the datetime from API response
            val apiTime = response.datetime
            if (apiTime.isNotEmpty()) {
                val timeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val date = timeFormat.parse(apiTime.substring(0, 19)) // Remove microseconds and timezone info
                
                // Format for display
                val displayFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                _currentTime.value = displayFormat.format(date ?: Date())
            } else {
                updateTime()
            }
        } catch (e: Exception) {
            // Fallback to local time if API fails
            updateTime()
        }
    }

    // Fallback method using local time (kept for error handling)
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