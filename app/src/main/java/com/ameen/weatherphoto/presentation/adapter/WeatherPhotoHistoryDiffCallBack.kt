package com.ameen.weatherphoto.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb

object WeatherPhotoHistoryDiffCallBack : DiffUtil.ItemCallback<PhotoDb>() {
    override fun areItemsTheSame(oldItem: PhotoDb, newItem: PhotoDb): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoDb, newItem: PhotoDb): Boolean {
        return oldItem == newItem
    }
}