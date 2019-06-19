package me.himanshusoni.basicextensions

import android.text.Spannable
import android.text.style.ForegroundColorSpan

fun String.mask(visibleFirst: Int, visibleLast: Int): String {
    var output = ""
    for (i in 0 until this.length) {
        if (i < visibleFirst || i >= (this.length - visibleLast)) {
            output += this[i]
        } else {
            output += "*"
        }
    }
    return output
}


fun String.highlight(query: String?, highlightColor: Int): Spannable {
    query?.let {
        if (!it.isBlank() && this.toLowerCase().contains(it.toLowerCase())) {
            var startPos = this.toLowerCase().indexOf(it.toLowerCase(), 0)
            val spanText = Spannable.Factory.getInstance().newSpannable(this)

            var hasMore: Boolean
            do {
                val endPos = startPos + it.length
                spanText.setSpan(
                    ForegroundColorSpan(highlightColor),
                    startPos,
                    endPos,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                startPos = this.toLowerCase().indexOf(it.toLowerCase(), endPos)
                hasMore = startPos != -1
            } while (hasMore)

            return spanText
        } else {
            return Spannable.Factory.getInstance().newSpannable(this)
        }
    } ?: run {
        return Spannable.Factory.getInstance().newSpannable(this)
    }
}