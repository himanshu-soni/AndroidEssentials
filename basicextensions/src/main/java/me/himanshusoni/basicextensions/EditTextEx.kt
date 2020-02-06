package me.himanshusoni.basicextensions

import com.google.android.material.textfield.TextInputLayout
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.StrikethroughSpan
import android.widget.EditText
import android.widget.TextView

interface KotlinTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}

fun EditText.beforeTextChange(action: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit) {
    addTextChangedListener(object : KotlinTextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            action.invoke(s, start, count, after)
        }
    })
}

fun EditText.afterTextChanged(action: (s: Editable?) -> Unit) {
    addTextChangedListener(object : KotlinTextWatcher {
        override fun afterTextChanged(s: Editable?) {
            action.invoke(s)
        }
    })
}

fun EditText.onTextChanged(action: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit) {
    addTextChangedListener(object : KotlinTextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            action.invoke(s, start, before, count)
        }
    })
}

fun TextInputLayout.errorNull() {
    isErrorEnabled = false
    error = null
}

fun EditText.empty() = setText("")

fun TextView.setTextOrHide(text: String?) {
    if (text.isNullOrEmpty()) {
        this.gone()
    } else {
        this.text = text
        this.visible()
    }
}

fun TextView.setStrikeThroughText(text: String) {
    val spannable = SpannableString(text)
    spannable.setSpan(StrikethroughSpan(), 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    setText(spannable, TextView.BufferType.SPANNABLE)
}