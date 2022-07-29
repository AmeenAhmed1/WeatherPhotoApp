package com.ameen.weatherphoto.domain.repository

import androidx.lifecycle.LiveData
import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb

interface IWeatherPhotoHistoryRepository {
    suspend fun insertNewCapturedPhotoIntoHistory(photo: PhotoDb): Long
    //fun getAllCapturedPhotoFromHistory(): LiveData<List<PhotoDb>>
}