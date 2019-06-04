package me.himanshusoni.basicextensions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat

fun Double.round(places: Int): Double {
    if (places < 0) throw IllegalArgumentException()

    var bd = BigDecimal(this)
    bd = bd.setScale(places, RoundingMode.HALF_UP)
    return bd.toDouble()
}

@JvmOverloads
fun Number.format(scale: Int, grouping: Boolean = true, forceScale: Boolean = true): String {
    val numberFormat = NumberFormat.getInstance()
    numberFormat.maximumFractionDigits = scale
    numberFormat.isGroupingUsed = grouping
    if (forceScale) numberFormat.minimumFractionDigits = scale
    return numberFormat.format(this)
}

fun String.toDoubleOrZero(): Double = if (this.toDoubleOrNull() == null) 0.toDouble() else this.toDouble()
fun String.toIntOrZero(): Int = if (this.toIntOrNull() == null) 0 else this.toInt()
fun Number.toStringOrBlank(): String = if (this == 0) "" else this.toString()