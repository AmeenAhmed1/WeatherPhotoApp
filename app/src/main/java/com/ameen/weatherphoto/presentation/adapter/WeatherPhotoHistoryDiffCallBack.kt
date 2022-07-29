package com.ameen.weatherphoto.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData

object WeatherPhotoHistoryDiffCallBack : DiffUtil.ItemCallback<WeatherPhotoHistoryData>() {
    override fun areItemsTheSame(
        oldItem: WeatherPhotoHistoryData,
        newItem: WeatherPhotoHistoryData
    ): Boolean {
        return oldItem.capturedImage == newItem.capturedImage
    }

    override fun areContentsTheSame(
        oldItem: WeatherPhotoHistoryData,
        newItem: WeatherPhotoHistoryData
    ): Boolean {
        return oldItem == newItem
    }
}