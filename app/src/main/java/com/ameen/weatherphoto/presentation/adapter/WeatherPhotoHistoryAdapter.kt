package com.ameen.weatherphoto.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb
import com.ameen.weatherphoto.databinding.ItemHistoryBinding
import com.ameen.weatherphoto.presentation.extentions.loadImage

class WeatherPhotoHistoryAdapter() :
    RecyclerView.Adapter<WeatherPhotoHistoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemHistoryBinding? = null

    private val differCallBack = object : DiffUtil.ItemCallback<PhotoDb>() {
        override fun areItemsTheSame(oldItem: PhotoDb, newItem: PhotoDb): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoDb, newItem: PhotoDb): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, differCallBack)

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
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onItemClickListener: ((PhotoDb) -> Unit)? = null
    fun onItemClicked(listener: (PhotoDb) -> Unit) {
        onItemClickListener = listener
    }
}