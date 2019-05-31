package me.himanshusoni.basicextensions

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.support.annotation.StringRes
import android.widget.Toast

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.toast(@StringRes msgRes: Int) = toast(getString(msgRes))

fun Context.isConnectedToNetwork(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun Context.isCameraAvailable(): Boolean = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
