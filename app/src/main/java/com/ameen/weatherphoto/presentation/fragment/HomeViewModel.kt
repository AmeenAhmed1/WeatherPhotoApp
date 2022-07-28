package com.ameen.weatherphoto.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.weatherphoto.core.ResultWrapper
import com.ameen.weatherphoto.data.model.WeatherResponse
import com.ameen.weatherphoto.domain.usecase.GetCurrentLocationWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentLocationWeatherUseCase: GetCurrentLocationWeatherUseCase
) : ViewModel() {

    var currentLocationLatitude: Double = 0.0
    var currentLocationLongitude: Double = 0.0

    private val _currentWeatherData: MutableStateFlow<ResultWrapper<WeatherResponse>> =
        MutableStateFlow(ResultWrapper.Loading)
    val currentWeatherData = _currentWeatherData


    fun getCurrentLocationWeather() =
        getCurrentLocationWeatherUseCase.execute(currentLocationLatitude, currentLocationLongitude)
            .flowOn(Dispatchers.IO)
            .onEach {
                _currentWeatherData.emit(it)
            }.launchIn(viewModelScope)

}