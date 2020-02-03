package com.android.baselibrary.sample

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelizeSample(var data: String?) : Parcelable