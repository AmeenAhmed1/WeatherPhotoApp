package com.ameen.weatherphoto.data.repository

import com.ameen.weatherphoto.core.API_KEY
import com.ameen.weatherphoto.core.ResultWrapper
import com.ameen.weatherphoto.data.datasource.WeatherApi
import com.ameen.weatherphoto.data.model.WeatherResponse
import com.ameen.weatherphoto.domain.repository.ICurrentLocationWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentLocationWeatherRepository @Inject constructor(val api: WeatherApi) :
    ICurrentLocationWeatherRepository {

    override fun getCurrentLocationWeather(
        lat: Double,
        lon: Double
    ): Flow<ResultWrapper<WeatherResponse>> {

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
                                it
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