package com.personal.timer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LapTimeDao {
    @Query("SELECT * FROM lap_times WHERE sessionId = :sessionId ORDER BY lapNumber")
    suspend fun getLapsBySession(sessionId: String): List<LapTimeEntity>

    @Insert
    suspend fun insertLap(lapTime: LapTimeEntity)

    @Insert
    suspend fun insertLaps(lapTimes: List<LapTimeEntity>)

    @Query("DELETE FROM lap_times WHERE sessionId = :sessionId")
    suspend fun deleteLapsBySession(sessionId: String)

    @Query("SELECT * FROM lap_times ORDER BY id DESC")
    fun getAllLaps(): Flow<List<LapTimeEntity>>
}