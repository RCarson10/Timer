package com.personal.timer.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LapTimeEntity::class],
    version = 1,
    exportSchema = false
)

abstract class TimerDatabase : RoomDatabase() {
    abstract fun lapTimeDao(): LapTimeDao
}