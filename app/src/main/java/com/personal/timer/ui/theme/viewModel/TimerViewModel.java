package com.personal.timer.ui.theme.viewModel;

import android.app.AlarmManager;

public class TimerViewModel {
    private AlarmManager alarmManager;

    // Add methods to manage timers, alarms, and other related functionalities
    // For example, startTimer(), stopTimer(), resetTimer(), etc.

    public void startTimer(long duration) {
        // Logic to start a timer with the specified duration
        alarmManager.setAlarmClock();
    }

    public void stopTimer() {
        // Logic to stop the currently running timer
    }

    public void resetTimer() {
        // Logic to reset the timer to its initial state
    }
}
