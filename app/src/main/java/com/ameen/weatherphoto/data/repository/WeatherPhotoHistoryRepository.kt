package com.ameen.weatherphoto.data.repository

import com.ameen.weatherphoto.data.datasource.local.WeatherPhotoDao
import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb
import com.ameen.weatherphoto.domain.repository.IWeatherPhotoHistoryRepository
import javax.inject.Inject

class WeatherPhotoHistoryRepository @Inject constructor(
    val weatherPhotoDao: WeatherPhotoDao
) : IWeatherPhotoHistoryRepository {

    override suspend fun insertNewCapturedPhotoIntoHistory(photo: PhotoDb): Long =
        weatherPhotoDao.insertNewPhoto(photo)

    override suspend fun getAllCapturedPhotoFromHistory(): List<PhotoDb> =
        weatherPhotoDao.getAllPhotos()
}