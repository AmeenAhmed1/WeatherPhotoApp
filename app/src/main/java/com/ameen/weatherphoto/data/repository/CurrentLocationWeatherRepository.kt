package com.ameen.weatherphoto.data.repository

import com.ameen.weatherphoto.core.util.API_KEY
import com.ameen.weatherphoto.core.wrapper.ResultWrapper
import com.ameen.weatherphoto.data.datasource.remote.WeatherApi
import com.ameen.weatherphoto.data.mapper.toDomainWeatherData
import com.ameen.weatherphoto.domain.model.WeatherData
import com.ameen.weatherphoto.domain.repository.ICurrentLocationWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentLocationWeatherRepository @Inject constructor(val api: WeatherApi) :
    ICurrentLocationWeatherRepository {

    override fun getCurrentLocationWeather(
        lat: Double,
        lon: Double
    ): Flow<ResultWrapper<WeatherData>> {

        return flow {
            try {
                val response = api.getCurrentWeather(
                    lat,
                    lon,
                    API_KEY
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(
                            ResultWrapper.Success(
                                //movieImageMapper.movieImagesResponseToViewState(it)
                                it.toDomainWeatherData()
                            )
                        )
                    } ?: emit(ResultWrapper.Error("Something Happen Please Try Again!!"))
                } else {
                    emit(ResultWrapper.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e.message.toString()))
            }
        }
    }
}