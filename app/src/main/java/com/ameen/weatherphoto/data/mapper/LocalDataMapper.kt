package com.ameen.weatherphoto.data.mapper

import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData

fun PhotoDb.toDomainWeatherHistoryData() =
    WeatherPhotoHistoryData(
        city = this.city,
        weatherCondition = this.weatherCondition,
        weatherConditionIcon = this.weatherConditionIcon,
        capturedImage = this.capturedImage
    )


fun WeatherPhotoHistoryData.toRoomWeatherHistoryData() =
    PhotoDb(
        city = this.city,
        weatherCondition = this.weatherCondition,
        weatherConditionIcon = this.weatherConditionIcon,
        capturedImage = this.capturedImage
    )