package me.himanshusoni.basicextensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment

fun Fragment.toast(msg: String) = activity?.toast(msg)
fun Fragment.toast(@StringRes msgRes: Int) = activity?.toast(msgRes)
