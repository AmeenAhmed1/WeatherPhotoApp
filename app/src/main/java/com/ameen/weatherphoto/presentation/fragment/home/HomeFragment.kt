package com.ameen.weatherphoto.presentation.fragment.home

import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ameen.weatherphoto.core.util.ApiEndPoints
import com.ameen.weatherphoto.core.util.ImageCameraUtils
import com.ameen.weatherphoto.core.util.LocationManager
import com.ameen.weatherphoto.core.util.LocationManagerInteraction
import com.ameen.weatherphoto.core.wrapper.ResultWrapper
import com.ameen.weatherphoto.databinding.FragmentHomeBinding
import com.ameen.weatherphoto.domain.model.WeatherData
import com.ameen.weatherphoto.domain.model.WeatherPhotoHistoryData
import com.ameen.weatherphoto.presentation.extentions.hide
import com.ameen.weatherphoto.presentation.extentions.loadImage
import com.ameen.weatherphoto.presentation.extentions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.io.File

@AndroidEntryPoint
class HomeFragment : Fragment(), LocationManagerInteraction {

    private val TAG = "HomeFragment"

    private lateinit var imageUtil: ImageCameraUtils
    private lateinit var file: File

    private lateinit var weatherData: WeatherData

    private val locationManager: LocationManager by lazy {
        LocationManager(this, this)
    }

    private val homeViewModel: HomeViewModel by viewModels()

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getCurrentLocation()
        initObservers()
        initClicks()
        openCameraHandler()

        return binding.root
    }

    private fun openCameraHandler() {

        val capturedImageFileUriObserver = MutableLiveData<Uri>()
        imageUtil = ImageCameraUtils(this, capturedImageFileUriObserver)

        capturedImageFileUriObserver.observe(viewLifecycleOwner) {
            binding.cameraHintText.hide()


            // Weather Data To Show overlay.
            val city = weatherData.weatherCity
            val weatherCondition = weatherData.weatherCondition
            val weatherConditionIcon =
                "${ApiEndPoints.CURRENT_WEATHER_CONDITION_ICON_ENDPOINT}${weatherData.weatherConditionIcon}.png"

            binding.currentLocationImageData.cityTextView.text = city
            binding.currentLocationImageData.weatherTextView.text = weatherCondition
            binding.currentLocationImageData.weatherIcon.loadImage(weatherConditionIcon)
            binding.currentLocationImageData.root.show()

            //Show Captured Image from Camera.
            binding.imageCamera.loadImage(it)

            // Automatically save captured Image into History.
            saveCapturedImageWithWeatherDataIntoHistory(
                WeatherPhotoHistoryData(
                    city = city,
                    weatherCondition = weatherCondition,
                    weatherConditionIcon = weatherConditionIcon,
                    capturedImage = it.path ?: ""
                )
            )

        }

        binding.imageCamera.setOnClickListener {
            imageUtil.openCamera()
        }

    }

    private fun getCurrentLocation() {
        locationManager.startLocationUpdates()
    }

    private fun getCurrentLocationWeather() {
        homeViewModel.getCurrentLocationWeather()
    }

    private fun initObservers() {

        lifecycleScope.launchWhenStarted {
            homeViewModel.currentWeatherData.collectLatest {

                when (it) {

                    is ResultWrapper.Loading -> {}

                    is ResultWrapper.Success -> {
                        Log.e(TAG, "Success: $it")
                        weatherData = it.value
                        binding.currentLocationData.locationAddress.text =
                            "${binding.currentLocationData.locationAddress.text} - ${it.value.weatherCondition}"

                    }

                    is ResultWrapper.Error -> {
                        Log.e(TAG, "Error: ${it.error}")
                    }
                }
            }
        }

    }

    private fun saveCapturedImageWithWeatherDataIntoHistory(newPhoto: WeatherPhotoHistoryData) {
        val saved = homeViewModel.insertCapturedWeatherPhoto(newPhoto)
        Log.e(TAG, "saveCapturedImageWithWeatherDataIntoHistory: $saved")
    }

    private fun initClicks() {
        binding.historyButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHistoryFragment()
            findNavController().navigate(action)
        }
    }

    override fun onLocationRetrieved(location: Location?, address: String) {
        if (location != null) {
            Log.e(TAG, "onLocationRetrieved: ${location.latitude}, ${location.longitude}")
            Log.e(TAG, "onLocationRetrieved: Address: $address")

            homeViewModel.currentLocationLatitude = location.latitude
            homeViewModel.currentLocationLongitude = location.longitude

            binding.currentLocationData.locationAddress.text = address

            getCurrentLocationWeather()
            locationManager.stopLocationUpdates()
        }
    }

    override fun onResume() {
        super.onResume()

        if (!this::weatherData.isInitialized)
            getCurrentLocation()
    }

    override fun onStop() {
        super.onStop()
        locationManager.stopLocationUpdates()
    }

}