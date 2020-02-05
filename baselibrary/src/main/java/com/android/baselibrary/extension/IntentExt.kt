package com.android.baselibrary.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat


fun Context.shareText(title: String?, text: String?) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
    }
    text?.let {
        sendIntent.apply {
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
    }
    val shareIntent = Intent.createChooser(sendIntent, title)
    startActivity(shareIntent)
}

fun Context.shareFile(title: String?, uri: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
    }
    sendIntent.apply {
        putExtra(Intent.EXTRA_STREAM, uri)
        type = uri.getMimeType()
    }

    val shareIntent = Intent.createChooser(sendIntent, title)
    startActivity(shareIntent)
}

fun Context.call(number: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$number")
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}


fun Context.openInBrowser(url: String) {
    try {
        val url = Uri.parse(url)
        val intent = CustomTabsIntent.Builder()
            //.setToolbarColor(getResourceColor(R.attr.colorPrimary))
            .build()
        intent.launchUrl(this, url)
    } catch (e: Exception) {
        toast(e.message)
    }
}