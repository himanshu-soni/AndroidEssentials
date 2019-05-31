package me.himanshusoni.basicextensions

import android.content.res.Resources
import android.util.TypedValue


fun Number.toPixels(unit: Int): Number =
    TypedValue.applyDimension(unit, this.toFloat(), Resources.getSystem().displayMetrics)

fun Number.dpToPx(): Number = this.toPixels(TypedValue.COMPLEX_UNIT_DIP)
fun Number.spToPx(): Number = this.toPixels(TypedValue.COMPLEX_UNIT_SP)
fun Number.pxToDp(): Number = (this.toInt() / Resources.getSystem().displayMetrics.density)