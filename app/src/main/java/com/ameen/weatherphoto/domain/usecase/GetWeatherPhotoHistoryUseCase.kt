package com.ameen.weatherphoto.domain.usecase

import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData
import com.ameen.weatherphoto.domain.repository.IWeatherPhotoHistoryRepository
import javax.inject.Inject

class GetWeatherPhotoHistoryUseCase @Inject constructor(val repo: IWeatherPhotoHistoryRepository) {
    suspend fun execute(): List<WeatherPhotoHistoryData> {
        return repo.getAllCapturedPhotoFromHistory()
    }
}