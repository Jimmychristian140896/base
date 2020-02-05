package com.android.baselibrary.extension

import android.Manifest
import android.Manifest.permission.READ_PHONE_STATE
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresPermission

@RequiresPermission(value = READ_PHONE_STATE)
fun Context.getDeviceId() : String{
    return try {
        val telephonyManager =
            getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.deviceId
    }catch (e: Exception) {
        ""
    }
}