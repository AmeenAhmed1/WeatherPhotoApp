package com.ameen.weatherphoto.domain.repository

import com.ameen.weatherphoto.core.ResultWrapper
import com.ameen.weatherphoto.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface ICurrentLocationWeatherRepository {
    fun getCurrentLocationWeather(
        lat: Double,
        lon: Double
    ): Flow<ResultWrapper<WeatherResponse>>
}