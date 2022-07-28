package com.ameen.weatherphoto.data.datasource

import com.ameen.weatherphoto.core.ApiEndPoints
import com.ameen.weatherphoto.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(ApiEndPoints.CURRENT_WEATHER_ENDPOINT)
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
    ): Response<WeatherResponse>

}