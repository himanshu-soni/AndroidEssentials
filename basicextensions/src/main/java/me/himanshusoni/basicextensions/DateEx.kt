package me.himanshusoni.basicextensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Date.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}


fun Date.format(format: String): String {
    val df = SimpleDateFormat(format, Locale.getDefault())
    return df.format(this)
}

fun String.asDate(pattern: String): Date {
    val df = SimpleDateFormat(pattern, Locale.getDefault())
    return df.parse(this)
}

fun String.asDateOrNull(pattern: String): Date? {
    return try {
        this.asDate(pattern)
    } catch (e: ParseException) {
        null
    }
}

fun Calendar.startOfDay(): Calendar {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
    return this
}

fun Calendar.endOfDay(): Calendar {
    set(Calendar.HOUR_OF_DAY, 23)
    set(Calendar.MINUTE, 59)
    set(Calendar.SECOND, 59)
    set(Calendar.MILLISECOND, 999)
    return this
}