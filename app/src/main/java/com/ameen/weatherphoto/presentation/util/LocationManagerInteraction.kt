package com.ameen.weatherphoto.presentation.util

import android.location.Location

interface LocationManagerInteraction {
    fun onLocationRetrieved(location: Location?, address: String)
}