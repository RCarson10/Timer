// app/src/main/java/com/personal/timer/repository/TimerRepository.kt
package com.personal.timer.repository

import com.personal.timer.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimerRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getTime(timeZone: String = "America/Phoenix") = apiService.getTimeForTimezone(
        timeZone
    )
}