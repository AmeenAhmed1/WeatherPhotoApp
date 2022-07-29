package com.ameen.weatherphoto.data.datasource.remote.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)