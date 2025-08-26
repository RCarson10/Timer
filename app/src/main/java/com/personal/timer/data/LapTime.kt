package com.personal.timer.data

data class LapTime(
    val lapNumber: Int,
    val time: Long,
    val splitTime: Long // time since previous lap
)