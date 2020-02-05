package com.android.baselibrary.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.android.baselibrary.R
import com.android.baselibrary.extension.askPermission
import com.android.baselibrary.extension.getLocation
import com.android.baselibrary.extension.isHasPermission

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
        GlobalScope.launch {
            delay(100)
        }

        thread {

        }

        runBlocking {

        }
        run {  }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        showCurrentLocation()


    }

    private fun showCurrentLocation() {
        val STORAGE_STORAGE_REQUEST_CODE = 0
        val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (!isHasPermission(*permissions))
            askPermission(permissions = *permissions, requestCode = STORAGE_STORAGE_REQUEST_CODE)
        else {
            //handle already granted permission

            getLocation {
                // Add a marker in Sydney and move the camera
                it?.let {
                    val sydney = LatLng(it.latitude, it.longitude)
                    mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            showCurrentLocation()
        }
    }


}
