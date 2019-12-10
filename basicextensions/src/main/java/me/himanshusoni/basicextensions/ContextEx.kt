package me.himanshusoni.basicextensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import androidx.annotation.StringRes
import android.widget.Toast
import java.io.File


fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
fun Context.toast(@StringRes msgRes: Int) = toast(getString(msgRes))

fun Context.isConnectedToNetwork(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun Context.isCameraAvailable(): Boolean = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)

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