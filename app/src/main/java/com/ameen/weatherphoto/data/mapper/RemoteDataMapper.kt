package com.ameen.weatherphoto.data.mapper

import com.ameen.weatherphoto.data.datasource.remote.model.WeatherResponse
import com.ameen.weatherphoto.domain.model.WeatherData

fun WeatherResponse.toDomainWeatherData() =
    WeatherData(
        weatherCity = "${this.name} - ${this.sys.country}",
        weatherCondition = this.weather.first().main,
        weatherConditionIcon = this.weather.first().icon
    )
