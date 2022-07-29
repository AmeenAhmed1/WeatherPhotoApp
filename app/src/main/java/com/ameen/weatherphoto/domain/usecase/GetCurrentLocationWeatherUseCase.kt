package com.ameen.weatherphoto.domain.usecase

import com.ameen.weatherphoto.core.wrapper.ResultWrapper
import com.ameen.weatherphoto.domain.model.WeatherData
import com.ameen.weatherphoto.domain.repository.ICurrentLocationWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentLocationWeatherUseCase @Inject constructor(val repo: ICurrentLocationWeatherRepository) {
    fun execute(lat: Double, lon: Double): Flow<ResultWrapper<WeatherData>> {
        return repo.getCurrentLocationWeather(lat, lon)
    }
}