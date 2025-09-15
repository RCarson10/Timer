package com.personal.timer.repository

import com.personal.timer.data.LapTime
import com.personal.timer.data.LapTimeDao
import com.personal.timer.data.LapTimeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StopwatchRepository @Inject constructor(
    private val lapTimeDao: LapTimeDao
) {
    private var currentSessionId: String? = null

    fun startNewSession(): String {
        currentSessionId = UUID.randomUUID().toString()
        return currentSessionId!!
    }

    suspend fun saveLap(lapTime: LapTime) {
        val sessionId = currentSessionId ?: startNewSession()
        val entity = LapTimeEntity(
            lapNumber = lapTime.lapNumber,
            totalTime = lapTime.time,
            splitTime = lapTime.splitTime,
            sessionId = sessionId
        )
        lapTimeDao.insertLap(entity)
    }

    suspend fun getLapsForCurrentSession(): List<LapTime> {
        val sessionID = currentSessionId ?: return emptyList()
        return lapTimeDao.getLapsBySession(sessionID).map {
            LapTime(
                lapNumber = it.lapNumber,
                time = it.totalTime,
                splitTime = it.splitTime
            )
        }
    }

    fun getAllLaps(): Flow<List<LapTime>> {
        return lapTimeDao.getAllLaps().map { entities ->
            entities.map { entity ->
                LapTime(
                    lapNumber = entity.lapNumber,
                    time = entity.totalTime,
                    splitTime = entity.splitTime
                )
            }
        }
    }

    suspend fun clearCurrentSession() {
        currentSessionId?.let { sessionId ->
            lapTimeDao.deleteLapsBySession(sessionId)
            currentSessionId = null
        }
    }


}