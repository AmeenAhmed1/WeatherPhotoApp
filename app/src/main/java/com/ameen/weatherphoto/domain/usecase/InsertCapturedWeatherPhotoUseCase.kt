package com.ameen.weatherphoto.domain.usecase

import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData
import com.ameen.weatherphoto.domain.repository.IWeatherPhotoHistoryRepository
import javax.inject.Inject

class InsertCapturedWeatherPhotoUseCase @Inject constructor(val repo: IWeatherPhotoHistoryRepository) {
    suspend fun execute(newPhoto: WeatherPhotoHistoryData): Long {
        return repo.insertNewCapturedPhotoIntoHistory(newPhoto)
    }
}