package com.ameen.weatherphoto.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Photos")
data class PhotoDb(
    @PrimaryKey val id: Int? = null,
    val city: String,
    val weatherCondition: String,
    val weatherConditionIcon: String,
    val capturedImage: String
) : Serializable
