package com.android.baselibrary.extension

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes


fun Context.playSound(@RawRes rawId: Int) {
    val player = MediaPlayer.create(this, rawId)
    player.start()
}