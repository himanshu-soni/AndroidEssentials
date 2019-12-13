package me.himashusoni.androidessentials.gsonextensions

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive

fun JsonObject.getOrNull(memberName: String): JsonElement? = get(memberName)

fun JsonObject.getAsJsonPrimitiveOrNull(memberName: String): JsonPrimitive? = try {
    getAsJsonPrimitive(memberName)
} catch (e: ClassCastException) {
    null
}

fun JsonObject.getAsJsonArrayOrNull(memberName: String): JsonArray? = try {
    getAsJsonArray(memberName)
} catch (e: ClassCastException) {
    null
}

fun JsonObject.getAsJsonObjectOrNull(memberName: String): JsonObject? = try {
    getAsJsonObject(memberName)
} catch (e: ClassCastException) {
    null
}