package com.ameen.weatherphoto.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb

@Dao
interface WeatherPhotoDao {

    @Query("SELECT * from Photos")
    fun getAllPhotos(): LiveData<List<PhotoDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPhoto(photoDb: PhotoDb): Long
}