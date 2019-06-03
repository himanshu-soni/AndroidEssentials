package me.himanshusoni.basicextensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View

fun Fragment.toast(msg: String) = activity?.toast(msg)
fun Fragment.toast(@StringRes msgRes: Int) = activity?.toast(msgRes)

fun Fragment.hideKeyboard() = activity?.hideKeyboard()
fun Fragment.hideKeyboard(v: View) = activity?.hideKeyboard(v)

fun Fragment.isCameraAvailable(): Boolean = activity?.isCameraAvailable() == true