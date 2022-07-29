package com.ameen.weatherphoto.domain.usecase

import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb
import com.ameen.weatherphoto.domain.repository.IWeatherPhotoHistoryRepository
import javax.inject.Inject

class InsertCapturedWeatherPhotoUseCase @Inject constructor(val repo: IWeatherPhotoHistoryRepository) {
    suspend fun execute(photoDb: PhotoDb): Long {
        return repo.insertNewCapturedPhotoIntoHistory(photoDb)
    }
}