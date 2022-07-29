package com.ameen.weatherphoto.domain.repository

import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb

interface IWeatherPhotoHistoryRepository {
    suspend fun insertNewCapturedPhotoIntoHistory(photo: PhotoDb): Long
    suspend fun getAllCapturedPhotoFromHistory(): List<PhotoDb>
}