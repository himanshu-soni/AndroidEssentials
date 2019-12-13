package me.himashusoni.androidessentials.gsonextensions

import com.google.gson.*
import java.math.BigDecimal
import java.math.BigInteger


fun JsonElement.asJsonObjectOrNull(): JsonObject? =
    if (isJsonObject) asJsonObject else null

fun JsonElement.asJsonArrayOrNull(): JsonArray? =
    if (isJsonArray) asJsonArray else null

fun JsonElement.asJsonPrimitiveOrNull(): JsonPrimitive? =
    if (isJsonPrimitive) asJsonPrimitive else null

fun JsonElement.asJsonNullOrNull(): JsonNull? =
    if (isJsonNull) asJsonNull else null

fun JsonElement.asStringOrNull(): String? = asJsonPrimitiveOrNull()?.asStringOrNull()
fun JsonElement.asBooleanOrNull(): Boolean? = asJsonPrimitiveOrNull()?.asBooleanOrNull()
fun JsonElement.asNumberOrNull(): Number? = asJsonPrimitiveOrNull()?.asNumberOrNull()
fun JsonElement.asDoubleOrNull(): Double? = asJsonPrimitiveOrNull()?.asDoubleOrNull()
fun JsonElement.asFloatOrNull(): Float? = asJsonPrimitiveOrNull()?.asFloatOrNull()
fun JsonElement.asLongOrNull(): Long? = asJsonPrimitiveOrNull()?.asLongOrNull()
fun JsonElement.asShortOrNull(): Short? = asJsonPrimitiveOrNull()?.asShortOrNull()
fun JsonElement.asIntOrNull(): Int? = asJsonPrimitiveOrNull()?.asIntOrNull()
fun JsonElement.asByteOrNull(): Byte? = asJsonPrimitiveOrNull()?.asByteOrNull()
fun JsonElement.asCharacterOrNull(): Char? = asJsonPrimitiveOrNull()?.asCharacterOrNull()
fun JsonElement.asBigDecimalOrNull(): BigDecimal? = asJsonPrimitiveOrNull()?.asBigDecimalOrNull()
fun JsonElement.asBigIntegerOrNull(): BigInteger? = asJsonPrimitiveOrNull()?.asBigIntegerOrNull()

fun JsonElement.asStringOr(alternate: () -> String) =
    asStringOrNull() ?: run(alternate)

fun JsonElement.asBooleanOr(alternate: () -> Boolean) =
    asBooleanOrNull() ?: run(alternate)

fun JsonElement.asNumberOr(alternate: () -> Number) =
    asNumberOrNull() ?: run(alternate)

fun JsonElement.asDoubleOr(alternate: () -> Double) =
    asDoubleOrNull() ?: run(alternate)

fun JsonElement.asFloatOr(alternate: () -> Float) =
    asFloatOrNull() ?: run(alternate)

fun JsonElement.asLongOr(alternate: () -> Long) =
    asLongOrNull() ?: run(alternate)

fun JsonElement.asShortOr(alternate: () -> Short) =
    asShortOrNull() ?: run(alternate)

fun JsonElement.asIntOr(alternate: () -> Int) =
    asIntOrNull() ?: run(alternate)

fun JsonElement.asByteOr(alternate: () -> Byte) =
    asByteOrNull() ?: run(alternate)

fun JsonElement.asCharacterOr(alternate: () -> Char) =
    asCharacterOrNull() ?: run(alternate)

fun JsonElement.asBigDecimalOr(alternate: () -> BigDecimal) =
    asBigDecimalOrNull() ?: run(alternate)

fun JsonElement.asBigIntegerOr(alternate: () -> BigInteger) =
    asBigIntegerOrNull() ?: run(alternate)