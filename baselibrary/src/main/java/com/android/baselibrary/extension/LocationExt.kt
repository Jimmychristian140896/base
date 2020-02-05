package com.android.baselibrary.extension

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationServices

//@RequiresPermission(value = ACCESS_COARSE_LOCATION)
fun Context.getLocation(listener: (Location?) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location : Location? ->
            listener(location)
            // Got last known location. In some rare situations this can be null.
        }
}