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
    set(Calendar.HOUR_OF_DAY, getActualMinimum(Calendar.HOUR_OF_DAY))
    set(Calendar.MINUTE, getActualMinimum(Calendar.MINUTE))
    set(Calendar.SECOND, getActualMinimum(Calendar.SECOND))
    set(Calendar.MILLISECOND, getActualMinimum(Calendar.MILLISECOND))
    return this
}

fun Calendar.endOfDay(): Calendar {
    set(Calendar.HOUR_OF_DAY, getActualMaximum(Calendar.HOUR_OF_DAY))
    set(Calendar.MINUTE, getActualMaximum(Calendar.MINUTE))
    set(Calendar.SECOND, getActualMaximum(Calendar.SECOND))
    set(Calendar.MILLISECOND, getActualMaximum(Calendar.MILLISECOND))
    return this
}

fun Calendar.startOfWeek(): Calendar {
    set(Calendar.DAY_OF_WEEK, getActualMinimum(Calendar.DAY_OF_WEEK))
    startOfDay()
    return this
}

fun Calendar.endOfWeek(): Calendar {
    set(Calendar.DAY_OF_WEEK, getActualMaximum(Calendar.DAY_OF_WEEK))
    endOfDay()
    return this
}

fun Calendar.startOfMonth(): Calendar {
    set(Calendar.DAY_OF_MONTH, getActualMinimum(Calendar.DAY_OF_MONTH))
    startOfDay()
    return this
}

fun Calendar.endOfMonth(): Calendar {
    set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
    endOfDay()
    return this
}

fun Calendar.startOfYear(): Calendar {
    set(Calendar.DAY_OF_YEAR, getActualMinimum(Calendar.DAY_OF_YEAR))
    startOfDay()
    return this
}

fun Calendar.endOfYear(): Calendar {
    set(Calendar.DAY_OF_YEAR, getActualMaximum(Calendar.DAY_OF_YEAR))
    endOfDay()
    return this
}