package com.ameen.weatherphoto.presentation.listener

import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData

interface WeatherHistoryItemClickListener {
    fun onWeatherHistoryItemClicked(selectedPhoto: WeatherPhotoHistoryData)
}