package com.android.baselibrary.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun Context.decodeBitmap(resId: Int): Bitmap? = BitmapFactory.decodeResource(resources, resId)