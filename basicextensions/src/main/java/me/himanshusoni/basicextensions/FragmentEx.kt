package me.himanshusoni.basicextensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.View

fun Fragment.toast(msg: String) = activity?.toast(msg)
fun Fragment.toast(@StringRes msgRes: Int) = activity?.toast(msgRes)

fun Fragment.hideKeyboard() = activity?.hideKeyboard()
fun Fragment.hideKeyboard(v: View) = activity?.hideKeyboard(v)

fun Fragment.isCameraAvailable(): Boolean = activity?.isCameraAvailable() == true

fun Fragment.alert(
    msg: String,
    title: String = "",
    positiveBtn: String = getString(android.R.string.ok),
    positiveBtnClick: (() -> Unit)? = null,
    negativeBtn: String = "",
    negativeBtnClick: (() -> Unit)? = null
) {
    activity?.alert(msg, title, positiveBtn, positiveBtnClick, negativeBtn, negativeBtnClick)
}

fun Fragment.alert(block: AlertDialog.Builder.() -> Unit) {
    activity?.alert(block)
}