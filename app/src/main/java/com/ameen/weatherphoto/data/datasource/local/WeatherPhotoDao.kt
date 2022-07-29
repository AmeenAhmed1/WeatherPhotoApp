package com.ameen.weatherphoto.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData

@Dao
interface WeatherPhotoDao {

    @Query("SELECT * from Photos")
    suspend fun getAllPhotos(): List<WeatherPhotoHistoryData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPhoto(photoDb: PhotoDb): Long
}