package com.ameen.weatherphoto.presentation.listener

import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb

interface WeatherHistoryItemClickListener {
    fun onWeatherHistoryItemClicked(selectedPhoto: PhotoDb)
}