package me.himanshusoni.basicextensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import android.view.View

fun androidx.fragment.app.Fragment.toast(msg: String) = activity?.toast(msg)
fun androidx.fragment.app.Fragment.toast(@StringRes msgRes: Int) = activity?.toast(msgRes)

fun androidx.fragment.app.Fragment.hideKeyboard() = activity?.hideKeyboard()
fun androidx.fragment.app.Fragment.hideKeyboard(v: View) = activity?.hideKeyboard(v)

fun androidx.fragment.app.Fragment.isCameraAvailable(): Boolean = activity?.isCameraAvailable() == true

fun androidx.fragment.app.Fragment.alert(
    msg: String,
    title: String = "",
    positiveBtn: String = getString(android.R.string.ok),
    positiveBtnClick: (() -> Unit)? = null,
    negativeBtn: String = "",
    negativeBtnClick: (() -> Unit)? = null
) {
    activity?.alert(msg, title, positiveBtn, positiveBtnClick, negativeBtn, negativeBtnClick)
}

fun androidx.fragment.app.Fragment.alert(block: AlertDialog.Builder.() -> Unit): AlertDialog? {
    return activity?.alert(block)
}