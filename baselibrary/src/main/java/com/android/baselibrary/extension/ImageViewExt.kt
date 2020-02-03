package com.android.baselibrary.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide

fun ImageView.loadFromUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadFromUrl(@RawRes @DrawableRes resourceId: Int) {
    Glide.with(context).load(resourceId).into(this)
}
