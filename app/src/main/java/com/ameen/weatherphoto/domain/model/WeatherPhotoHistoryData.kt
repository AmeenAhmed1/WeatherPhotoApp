package com.ameen.weatherphoto.domain.model

import java.io.Serializable

data class WeatherPhotoHistoryData(
    val city: String,
    val weatherCondition: String,
    val weatherConditionIcon: String,
    val capturedImage: String
) : Serializable
