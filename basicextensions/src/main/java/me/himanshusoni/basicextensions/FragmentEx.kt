package me.himanshusoni.basicextensions

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty

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

fun Fragment.alert(block: AlertDialog.Builder.() -> Unit): AlertDialog? =
    if (isResumed) activity?.alert(block) else null

fun Fragment.runOnUiThread(runBlock: () -> Unit) {
    activity?.runOnUiThread { runBlock() }
}

fun <T : Any> argument(): ReadWriteProperty<Fragment, T> =
    FragmentArgumentDelegate()

fun <T : Any> argumentNullable(): ReadWriteProperty<Fragment, T?> =
    FragmentNullableArgumentDelegate()
