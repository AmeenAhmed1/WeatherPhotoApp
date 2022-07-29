package com.ameen.weatherphoto.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photos")
data class WeatherPhotoEntity(
    @PrimaryKey val id: Int? = null,
    val city: String,
    val weatherCondition: String,
    val weatherConditionIcon: String,
    val capturedImage: String
)
