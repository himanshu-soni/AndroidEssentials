package me.himanshusoni.basicextensions

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean = visibility == View.VISIBLE
fun View.isInvisible(): Boolean = visibility == View.INVISIBLE
fun View.isGone(): Boolean = visibility == View.GONE

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.select() {
    isSelected = true
}

fun View.unselect() {
    isSelected = false
}