package me.himanshusoni.basicextensions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat

fun Double.round(places: Int): Double {
    require(places >= 0)

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

fun String?.toDoubleOrDefault(defaultValue: Double): Double =
    if (this?.toDoubleOrNull() == null) defaultValue else this.toDouble()

fun String?.toDoubleOrZero(): Double = toDoubleOrDefault(0.0)

fun String?.toIntOrDefault(defaultValue: Int): Int =
    if (this?.toIntOrNull() == null) defaultValue else this.toInt()

fun String?.toIntOrZero(): Int = toIntOrDefault(0)

fun String?.toLongOrDefault(defaultValue: Long): Long =
    if (this?.toLongOrNull() == null) defaultValue else this.toLong()

fun String?.toLongOrZero(): Long = toLongOrDefault(0)

fun String?.toFloatOrDefault(defaultValue: Float): Float =
    if (this?.toFloatOrNull() == null) defaultValue else this.toFloat()

fun String?.toFloatOrZero(): Float = toFloatOrDefault(0.0f)

fun Number?.toStringOrBlank(): String = if (this == 0) "" else this.toString()

fun String?.toNonNegativeInt(defaultValue: Int): Int {
    try {
        val value = this?.toLong() ?: return defaultValue
        return when {
            value > Int.MAX_VALUE -> Int.MAX_VALUE
            value < 0 -> 0
            else -> value.toInt()
        }
    } catch (_: NumberFormatException) {
        return defaultValue
    }
}