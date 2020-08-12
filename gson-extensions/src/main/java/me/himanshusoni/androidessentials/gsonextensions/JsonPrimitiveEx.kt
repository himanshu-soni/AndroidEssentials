package me.himanshusoni.androidessentials.gsonextensions

import com.google.gson.JsonPrimitive
import java.math.BigDecimal
import java.math.BigInteger

fun JsonPrimitive.asStringOrNull(): String? = if (isString) asString else null
fun JsonPrimitive.asBooleanOrNull(): Boolean? = if (isBoolean) asBoolean else null
fun JsonPrimitive.asNumberOrNull(): Number? = if (isNumber) asNumber else null

fun JsonPrimitive.asDoubleOrNull(): Double? = try {
    asDouble
} catch (e: NumberFormatException) {
    null
}

fun JsonPrimitive.asFloatOrNull(): Float? = try {
    asFloat
} catch (e: NumberFormatException) {
    null
}

fun JsonPrimitive.asLongOrNull(): Long? = try {
    asLong
} catch (e: NumberFormatException) {
    null
}

fun JsonPrimitive.asShortOrNull(): Short? = try {
    asShort
} catch (e: NumberFormatException) {
    null
}

fun JsonPrimitive.asIntOrNull(): Int? = try {
    asInt
} catch (e: NumberFormatException) {
    null
}

fun JsonPrimitive.asByteOrNull(): Byte? = try {
    asByte
} catch (e: NumberFormatException) {
    null
}

fun JsonPrimitive.asCharacterOrNull(): Char? = try {
    asCharacter
} catch (e: Exception) {
    null
}

fun JsonPrimitive.asBigDecimalOrNull(): BigDecimal? = try {
    asBigDecimal
} catch (e: NumberFormatException) {
    null
}

fun JsonPrimitive.asBigIntegerOrNull(): BigInteger? = try {
    asBigInteger
} catch (e: NumberFormatException) {
    null
}

fun JsonPrimitive.asStringOr(alternate: () -> String) =
    if (isString) asString else run(alternate)

fun JsonPrimitive.asBooleanOr(alternate: () -> Boolean) =
    if (isBoolean) asBoolean else run(alternate)

fun JsonPrimitive.asNumberOr(alternate: () -> Number) =
    if (isNumber) asNumber else run(alternate)

fun JsonPrimitive.asDoubleOr(alternate: () -> Double) =
    asDoubleOrNull() ?: run(alternate)

fun JsonPrimitive.asFloatOr(alternate: () -> Float) =
    asFloatOrNull() ?: run(alternate)

fun JsonPrimitive.asLongOr(alternate: () -> Long) =
    asLongOrNull() ?: run(alternate)

fun JsonPrimitive.asShortOr(alternate: () -> Short) =
    asShortOrNull() ?: run(alternate)

fun JsonPrimitive.asIntOr(alternate: () -> Int) =
    asIntOrNull() ?: run(alternate)

fun JsonPrimitive.asByteOr(alternate: () -> Byte) =
    asByteOrNull() ?: run(alternate)

fun JsonPrimitive.asCharacterOr(alternate: () -> Char) =
    asCharacterOrNull() ?: run(alternate)

fun JsonPrimitive.asBigDecimalOr(alternate: () -> BigDecimal) =
    asBigDecimalOrNull() ?: run(alternate)

fun JsonPrimitive.asBigIntegerOr(alternate: () -> BigInteger) =
    asBigIntegerOrNull() ?: run(alternate)