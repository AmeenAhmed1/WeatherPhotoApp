package com.ameen.weatherphoto.core.util

import android.location.Location

interface LocationManagerInteraction {
    fun onLocationRetrieved(location: Location?, address: String)
}