package com.personal.timer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lap_times")
data class LapTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val lapNumber: Int,
    val totalTime: Long, // Total time from start
    val splitTime: Long, // Time since previous lap
    val sessionId: String // To group laps by session
)