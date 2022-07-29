package com.ameen.weatherphoto.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ameen.weatherphoto.data.datasource.local.model.WeatherPhotoEntity

@Database(entities = [WeatherPhotoEntity::class], version = 2, exportSchema = false)
abstract class WeatherAppDataBase : RoomDatabase() {
    abstract val weatherPhotoDao: WeatherPhotoDao
}