package com.personal.timer.di

import android.content.Context
import androidx.room.Room
import com.personal.timer.data.LapTimeDao
import com.personal.timer.data.TimerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTimerDatabase(@ApplicationContext context: Context): TimerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TimerDatabase::class.java,
            "timer_database"
        ).build()
    }

    @Provides
    fun provideLapTimeDao(database: TimerDatabase): LapTimeDao {
        return database.lapTimeDao()
    }
}