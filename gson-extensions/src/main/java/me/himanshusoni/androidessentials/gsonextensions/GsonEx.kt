package me.himanshusoni.androidessentials.gsonextensions

import com.google.gson.*
import java.math.BigDecimal
import java.math.BigInteger

fun Any.toGsonString(): String = Gson().toJson(this)
inline fun <reified T : Any> String.fromGsonString(): T = Gson().fromJson(this, T::class.java)

