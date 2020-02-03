package com.android.baselibrary.extension

import android.app.ActivityManager
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.PowerManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.android.baselibrary.R

fun Context.isInternetAvailable(): Boolean {
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

inline fun Context.notification(
    channelId: String,
    func: NotificationCompat.Builder.() -> Unit
): Notification {
    val builder = NotificationCompat.Builder(this, channelId)
    builder.func()
    return builder.build()
}

fun Context.sendLocalBroadcast(intent: Intent) {
    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
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

inline fun <reified W : ListenableWorker> Context.enqueueWorker(func: OneTimeWorkRequest.Builder.() -> Unit) {
    val builder = OneTimeWorkRequestBuilder<W>()
    builder.func()
    val workerRequest = builder.build()
    WorkManager.getInstance(this.applicationContext).enqueue(workerRequest)
}


val Context.notificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
val Context.powerManager: PowerManager
    get() = getSystemService(Context.POWER_SERVICE) as PowerManager
val Context.activityManager: ActivityManager
    get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

fun Context.toast(@StringRes resource: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resource, duration).show()
}

fun Context.toast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text.orEmpty(), duration).show()
}

fun Context.drawable(@DrawableRes drawableResource: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResource)

fun Context.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun Context.dismissKeyboard(view: View?) {
    view?.let{
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}
