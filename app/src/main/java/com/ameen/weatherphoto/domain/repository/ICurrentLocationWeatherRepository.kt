package com.ameen.weatherphoto.domain.repository

import com.ameen.weatherphoto.core.wrapper.ResultWrapper
import com.ameen.weatherphoto.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface ICurrentLocationWeatherRepository {
    fun getCurrentLocationWeather(
        lat: Double,
        lon: Double
    ): Flow<ResultWrapper<WeatherData>>
}