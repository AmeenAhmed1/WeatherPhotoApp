package com.ameen.weatherphoto.data.mapper

import com.ameen.weatherphoto.data.datasource.local.model.WeatherPhotoEntity
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData

fun WeatherPhotoEntity.toDomainWeatherHistoryData() =
    WeatherPhotoHistoryData(
        city = this.city,
        weatherCondition = this.weatherCondition,
        weatherConditionIcon = this.weatherConditionIcon,
        capturedImage = this.capturedImage
    )


fun WeatherPhotoHistoryData.toRoomWeatherHistoryData() =
    WeatherPhotoEntity(
        city = this.city,
        weatherCondition = this.weatherCondition,
        weatherConditionIcon = this.weatherConditionIcon,
        capturedImage = this.capturedImage
    )