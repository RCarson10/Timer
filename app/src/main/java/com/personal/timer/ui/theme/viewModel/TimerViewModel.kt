package com.personal.timer.ui.theme.viewModel

import android.app.AlarmManager
import android.app.PendingIntent
import android.os.Build
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private lateinit var alarmManager: AlarmManager

    fun init(alarmManager: AlarmManager) {
        this.alarmManager = alarmManager
    }

    fun startTimer(hours: Int, minutes: Int, seconds: Int, pendingIntent: PendingIntent) {
        val alarmInformation = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis() + (hours * 3600000) + (minutes * 60000) + (seconds * 1000),
            git pendingIntent)



        // System-level timer (works when app is backgrounded)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmInformation.triggerTime,
            alarmInformation.showIntent
        )
    }
    private fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            // On older versions, assume we can schedule exact alarms
            true
        }
    }
}

