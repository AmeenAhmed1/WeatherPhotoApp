package com.ameen.weatherphoto.core.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.ameen.weatherphoto.presentation.extentions.showToast
import com.github.dhaval2404.imagepicker.ImagePicker


class ImageCameraUtils(private val fragment: Fragment, private val response: MutableLiveData<Uri>) {

    fun openCamera() {
        if (ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission.launch(Manifest.permission.CAMERA)
        } else {
            captureImage()
        }
    }


    private fun captureImage() {
        ImagePicker.with(fragment)
            .crop()
            .cameraOnly()
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            ).createIntent {
                getResults.launch(it)
            }
    }


    private val requestPermission =
        fragment.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                captureImage()
            }
        }


    private val getResults =
        fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = result.data?.data

                response.value = fileUri!!

            } else if (result.resultCode == ImagePicker.RESULT_ERROR) {
                Toast(fragment.requireContext()).showToast(
                    fragment.requireActivity(),
                    ImagePicker.getError(result.data)
                )
            }

        }
}