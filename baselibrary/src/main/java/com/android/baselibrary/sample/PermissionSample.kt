package com.android.baselibrary.sample

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import com.android.baselibrary.extension.askPermission
import com.android.baselibrary.extension.isHasPermission

fun checkPermission(activity: AppCompatActivity) {
    val STORAGE_STORAGE_REQUEST_CODE = 0
    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    if (!activity.isHasPermission(*permissions))
        activity.askPermission(permissions = *permissions, requestCode = STORAGE_STORAGE_REQUEST_CODE)
    else
    {
        //handle already granted permission
    }

}