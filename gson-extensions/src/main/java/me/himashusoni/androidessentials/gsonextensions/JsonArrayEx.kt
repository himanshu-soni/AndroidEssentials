package me.himashusoni.androidessentials.gsonextensions

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import java.math.BigDecimal
import java.math.BigInteger


fun JsonArray.setOrIgnore(index: Int, element: JsonElement): JsonElement? = try {
    set(index, element)
} catch (e: IndexOutOfBoundsException) {
    null
}

fun JsonArray.removeOrIgnore(index: Int): JsonElement? = try {
    remove(index)
} catch (e: IndexOutOfBoundsException) {
    null
}

fun JsonArray.getOrNull(index: Int): JsonElement? = try {
    get(index)
} catch (e: IndexOutOfBoundsException) {
    null
}

fun JsonArray.asStringOrNull(): String? = getOrNull(0)?.asStringOrNull()
fun JsonArray.asBooleanOrNull(): Boolean? = getOrNull(0)?.asBooleanOrNull()
fun JsonArray.asNumberOrNull(): Number? = getOrNull(0)?.asNumberOrNull()
fun JsonArray.asDoubleOrNull(): Double? = getOrNull(0)?.asDoubleOrNull()
fun JsonArray.asFloatOrNull(): Float? = getOrNull(0)?.asFloatOrNull()
fun JsonArray.asLongOrNull(): Long? = getOrNull(0)?.asLongOrNull()
fun JsonArray.asShortOrNull(): Short? = getOrNull(0)?.asShortOrNull()
fun JsonArray.asIntOrNull(): Int? = getOrNull(0)?.asIntOrNull()
fun JsonArray.asByteOrNull(): Byte? = getOrNull(0)?.asByteOrNull()
fun JsonArray.asCharacterOrNull(): Char? = getOrNull(0)?.asCharacterOrNull()
fun JsonArray.asBigDecimalOrNull(): BigDecimal? = getOrNull(0)?.asBigDecimalOrNull()
fun JsonArray.asBigIntegerOrNull(): BigInteger? = getOrNull(0)?.asBigIntegerOrNull()

