// app/src/main/java/com/personal/timer/network/ApiService.kt
package com.personal.timer.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/timezone/{timezone}")
    suspend fun getTimeForTimezone(@Path("timezone") timezone: String): WorldTimeResponse
}

data class WorldTimeResponse(
    val utc_offset: String,
    val timezone: String,
    val day_of_week: Int,
    val day_of_year: Int,
    val datetime: String,
    val utc_datetime: String,
    val unixtime: Long,
    val raw_offset: Int,
    val week_number: Int,
    val dst: Boolean,
    val abbreviation: String,
    val dst_offset: Int,
    val dst_from: String?,
    val dst_until: String?,
    val client_ip: String
)