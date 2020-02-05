package com.android.baselibrary.extension

import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap


fun String.getMimeType(): String? {

    var type: String? = null
    val extension = MimeTypeMap.getFileExtensionFromUrl(this)
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
    return type

}