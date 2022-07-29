package com.ameen.weatherphoto.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.ameen.weatherphoto.databinding.ItemHistoryBinding
import com.ameen.weatherphoto.presentation.extentions.loadImage
import com.ameen.weatherphoto.presentation.listener.WeatherHistoryItemClickListener

class WeatherPhotoHistoryAdapter(
    private val weatherPhotoHistoryItemClickListener: WeatherHistoryItemClickListener
) : RecyclerView.Adapter<WeatherPhotoHistoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemHistoryBinding? = null

    val diff = AsyncListDiffer(this, WeatherPhotoHistoryDiffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentData = diff.currentList[position]

        holder.binding.imageHistory.loadImage(currentData.capturedImage)
        holder.binding.currentLocationImageData.apply {
            cityTextView.text = currentData.city
            weatherTextView.text = currentData.weatherCondition
            weatherIcon.loadImage(currentData.weatherConditionIcon)
        }

        holder.binding.imageHistory.setOnClickListener {
            weatherPhotoHistoryItemClickListener.onWeatherHistoryItemClicked(currentData)
        }
    }

    override fun getItemCount(): Int = diff.currentList.size
}