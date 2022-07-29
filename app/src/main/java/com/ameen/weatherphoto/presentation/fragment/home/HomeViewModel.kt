package com.ameen.weatherphoto.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.weatherphoto.core.wrapper.ResultWrapper
import com.ameen.weatherphoto.domain.model.WeatherData
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData
import com.ameen.weatherphoto.domain.usecase.GetCurrentLocationWeatherUseCase
import com.ameen.weatherphoto.domain.usecase.InsertCapturedWeatherPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentLocationWeatherUseCase: GetCurrentLocationWeatherUseCase,
    private val insertCapturedWeatherPhotoUseCase: InsertCapturedWeatherPhotoUseCase
) : ViewModel() {

    var currentLocationLatitude: Double = 0.0
    var currentLocationLongitude: Double = 0.0

    private val _currentWeatherData: MutableStateFlow<ResultWrapper<WeatherData>> =
        MutableStateFlow(ResultWrapper.Loading)
    val currentWeatherData = _currentWeatherData


    fun getCurrentLocationWeather() =
        getCurrentLocationWeatherUseCase.execute(currentLocationLatitude, currentLocationLongitude)
            .flowOn(Dispatchers.IO)
            .onEach {
                _currentWeatherData.emit(it)
            }.launchIn(viewModelScope)


    fun insertCapturedWeatherPhoto(newPhoto: WeatherPhotoHistoryData) =
        viewModelScope.launch(Dispatchers.IO) {
            insertCapturedWeatherPhotoUseCase.execute(newPhoto)
        }

}