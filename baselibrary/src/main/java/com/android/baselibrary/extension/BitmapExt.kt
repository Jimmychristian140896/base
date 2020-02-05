package com.android.baselibrary.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.IntRange
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun Context.decodeBitmap(resId: Int): Bitmap? = BitmapFactory.decodeResource(resources, resId)

fun Bitmap.compress(@IntRange(from = 0, to = 100) quality: Int = 100): Bitmap {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, quality, stream)
    val byteArray = stream.toByteArray()
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

fun Bitmap.resizedBitmap(maxSize: Int): Bitmap {
    val image = this
    var width = image.width
    var height = image.height
    val bitmapRatio = width.toFloat() / height.toFloat()
    if (bitmapRatio > 1) {
        width = maxSize
        height = (width / bitmapRatio).toInt()
    } else {
        height = maxSize
        width = (height * bitmapRatio).toInt()
    }
    return Bitmap.createScaledBitmap(image, width, height, true)
}

fun File.toBitmap(): Bitmap = BitmapFactory.decodeFile(this.absolutePath)

fun Bitmap.toFile(file: File) : File {
    val bos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos)
    val bitmapdata = bos.toByteArray()

    val fos = FileOutputStream(file)
    fos.write(bitmapdata)
    fos.flush()
    fos.close()
    return file
}