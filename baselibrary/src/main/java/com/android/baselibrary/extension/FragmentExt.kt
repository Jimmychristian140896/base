package com.android.baselibrary.extension

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

fun Fragment.color(@ColorRes color: Int) = context?.color(color)

fun Fragment.drawable(@DrawableRes drawableResource: Int): Drawable? = context?.drawable(drawableResource)