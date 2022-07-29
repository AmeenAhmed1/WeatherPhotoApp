package com.ameen.weatherphoto.domain.repository

import com.ameen.weatherphoto.data.datasource.local.model.WeatherPhotoEntity
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData

interface IWeatherPhotoHistoryRepository {
    suspend fun insertNewCapturedPhotoIntoHistory(newPhoto: WeatherPhotoHistoryData): Long
    suspend fun getAllCapturedPhotoFromHistory(): List<WeatherPhotoHistoryData>
}