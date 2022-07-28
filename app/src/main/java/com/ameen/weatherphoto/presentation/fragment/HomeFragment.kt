package com.ameen.weatherphoto.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.ameen.weatherphoto.presentation.util.ImageCameraUtils
import com.ameen.weatherphoto.databinding.FragmentHomeBinding
import com.ameen.weatherphoto.presentation.extentions.hide
import com.ameen.weatherphoto.presentation.extentions.loadImage
import java.io.File

class HomeFragment : Fragment() {

    private lateinit var imageUtil: ImageCameraUtils
    private lateinit var file: File

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openCameraHandler()
    }

    private fun openCameraHandler() {

        val capturedImageFileUriObserver = MutableLiveData<Uri>()
        imageUtil = ImageCameraUtils(this, capturedImageFileUriObserver)

        capturedImageFileUriObserver.observe(viewLifecycleOwner) {
            binding.cameraHintText.hide()
            binding.imageCamera.loadImage(it)
        }

        binding.imageCamera.setOnClickListener {
            imageUtil.openCamera()
        }

    }
}