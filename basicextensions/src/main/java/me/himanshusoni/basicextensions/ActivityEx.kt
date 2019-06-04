package me.himanshusoni.basicextensions

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    this.currentFocus?.let { hideKeyboard(it) }
}

fun Activity.hideKeyboard(f: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (f.windowToken != null) {//&& EditText.class.isAssignableFrom(f.getClass())
        imm.hideSoftInputFromWindow(f.windowToken, 0)
    } else {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}

fun Activity.alert(
    msg: String,
    title: String = "",
    positiveBtn: String = getString(android.R.string.ok),
    positiveBtnClick: (() -> Unit)? = null,
    negativeBtn: String = "",
    negativeBtnClick: (() -> Unit)? = null
) {
    val d = AlertDialog.Builder(this)
    if (title.isNotEmpty()) d.setTitle(title)
    if (msg.isNotEmpty()) d.setMessage(msg)
    d.setPositiveButton(positiveBtn) { _, _ -> positiveBtnClick?.invoke() }
    if (negativeBtn.isNotEmpty()) d.setNegativeButton(negativeBtn) { _, _ -> negativeBtnClick?.invoke() }
    d.show().apply { }
}

fun Activity.alert(block: AlertDialog.Builder.() -> Unit) {
    val d = AlertDialog.Builder(this)
    d.block()
    d.show()
}