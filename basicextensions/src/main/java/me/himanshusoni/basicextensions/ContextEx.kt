package me.himanshusoni.basicextensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.StringRes
import java.io.File

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.toast(@StringRes msgRes: Int) = toast(getString(msgRes))

fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        @Suppress("DEPRECATION")
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false

        @Suppress("DEPRECATION")
        return nwInfo.isConnected
    }
}

fun Context.isCameraAvailable(): Boolean =
    packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)

fun Context.shareFile(file: File, header: String = "Share") {
    val uri = Uri.fromFile(file)
    shareUri(uri, header)
}

fun Context.shareUri(uri: Uri, header: String = "Share") {
    val sharingIntent = Intent(Intent.ACTION_SEND)
    sharingIntent.type = "*/*"
    sharingIntent.putExtra(Intent.EXTRA_STREAM, uri)
    startActivity(Intent.createChooser(sharingIntent, header))
}

fun Context.shareText(text: String, subject: String = "", header: String = "Share") {
    val sharingIntent = Intent(Intent.ACTION_SEND)
    sharingIntent.type = "text/plain"
    if (subject.isNotEmpty()) {
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
    startActivity(Intent.createChooser(sharingIntent, header))
}