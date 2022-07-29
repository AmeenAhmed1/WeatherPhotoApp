package com.ameen.weatherphoto.data.repository

import com.ameen.weatherphoto.data.datasource.local.WeatherPhotoDao
import com.ameen.weatherphoto.data.mapper.toDomainWeatherHistoryData
import com.ameen.weatherphoto.data.mapper.toRoomWeatherHistoryData
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData
import com.ameen.weatherphoto.domain.repository.IWeatherPhotoHistoryRepository
import javax.inject.Inject

class WeatherPhotoHistoryRepository @Inject constructor(
    val weatherPhotoDao: WeatherPhotoDao
) : IWeatherPhotoHistoryRepository {

    override suspend fun insertNewCapturedPhotoIntoHistory(newPhoto: WeatherPhotoHistoryData): Long =
        weatherPhotoDao.insertNewPhoto(newPhoto.toRoomWeatherHistoryData())

    override suspend fun getAllCapturedPhotoFromHistory(): List<WeatherPhotoHistoryData> =
        weatherPhotoDao.getAllPhotos().map {
            it.toDomainWeatherHistoryData()
        }
}