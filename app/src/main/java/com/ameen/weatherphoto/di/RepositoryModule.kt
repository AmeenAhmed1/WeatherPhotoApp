package com.ameen.weatherphoto.di

import com.ameen.weatherphoto.data.datasource.WeatherApi
import com.ameen.weatherphoto.data.repository.CurrentLocationWeatherRepository
import com.ameen.weatherphoto.domain.repository.ICurrentLocationWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesCurrentLocationWeatherRepository(api: WeatherApi) =
        CurrentLocationWeatherRepository(api) as ICurrentLocationWeatherRepository

}