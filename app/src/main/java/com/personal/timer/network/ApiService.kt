// app/src/main/java/com/personal/timer/network/ApiService.kt
package com.personal.timer.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("endpoint")
    suspend fun getData(@Query("param") param: String): ResponseData
}

data class ResponseData(
    val message: String,
    val data: Any
)