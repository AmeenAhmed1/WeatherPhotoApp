package com.ameen.weatherphoto.presentation.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ameen.weatherphoto.databinding.FragmentDetailsBinding
import com.ameen.weatherphoto.presentation.extentions.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private val selectedImageData by lazy {
        args.selectedImage
    }

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindSelectedDataToViews()

        return binding.root
    }

    private fun bindSelectedDataToViews(){
        binding.imageHistory.loadImage(selectedImageData.capturedImage)
        binding.currentLocationImageData.weatherIcon.loadImage(selectedImageData.weatherConditionIcon)
        binding.currentLocationImageData.weatherTextView.text = selectedImageData.weatherCondition
        binding.currentLocationImageData.cityTextView.text = selectedImageData.city
    }
}