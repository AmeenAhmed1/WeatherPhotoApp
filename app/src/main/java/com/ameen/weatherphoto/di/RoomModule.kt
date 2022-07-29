package com.ameen.weatherphoto.di

import android.content.Context
import androidx.room.Room
import com.ameen.weatherphoto.data.datasource.local.WeatherAppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            WeatherAppDataBase::class.java,
            "WeatherPhoto"
        ).fallbackToDestructiveMigration()
            .build()

}