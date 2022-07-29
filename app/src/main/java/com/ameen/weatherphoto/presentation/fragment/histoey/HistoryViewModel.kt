package com.ameen.weatherphoto.presentation.fragment.histoey

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.weatherphoto.data.datasource.local.model.PhotoDb
import com.ameen.weatherphoto.domain.usecase.GetWeatherPhotoHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    val getWeatherPhotoHistoryUseCase: GetWeatherPhotoHistoryUseCase
) : ViewModel() {

    private val TAG = "HistoryViewModel"

    private val _historyWeatherData: MutableLiveData<List<PhotoDb>> = MutableLiveData()
    val historyWeatherData = _historyWeatherData

    fun getAllWeatherPhotoHistory() = viewModelScope.launch(Dispatchers.IO) {
        val historyListResult = getWeatherPhotoHistoryUseCase.execute()
        Log.e(TAG, "getAllWeatherPhotoHistory: $historyListResult")
        _historyWeatherData.postValue(historyListResult)
    }

}