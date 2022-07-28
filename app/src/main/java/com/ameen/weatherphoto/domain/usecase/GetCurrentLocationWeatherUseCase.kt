package com.ameen.weatherphoto.domain.usecase

import com.ameen.weatherphoto.core.ResultWrapper
import com.ameen.weatherphoto.data.model.WeatherResponse
import com.ameen.weatherphoto.domain.repository.ICurrentLocationWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentLocationWeatherUseCase @Inject constructor(val repo: ICurrentLocationWeatherRepository) {
    fun execute(lat: Double, lon: Double): Flow<ResultWrapper<WeatherResponse>> {
        return repo.getCurrentLocationWeather(lat, lon)
    }
}