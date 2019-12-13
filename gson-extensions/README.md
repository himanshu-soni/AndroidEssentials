GsonExtensions
--------------
Null safe kotlin extensions for gson 

Convert any object to Json string
```kotlin
val jsonString = user.toGsonString()
```

and convert it back to object
```kotlin
val user: User = jsonString.fromGsonString()
```

Get or null
```kotlin
val mayBeNullElement = jsonObject.getOrNull("element")
val mayBeNullObject = jsonObject.getJsonObjectOrNull("object")
val mayBeNullArray = jsonObject.getJsonArrayOrNull("array")
```

Get or default alternate value
```kotlin
val integer = jsonElement.asIntegerOr { 0 }
val boolean = jsonElement.asBooleanOr { false }
val string = jsonElement.asStringOr { "N.A." }
val double = jsonElement.asDoubleOr { 0.0 }
```

And more... 

* **GsonEx.kt** - contains json-object conversion functions.
* **JsonArrayEx.kt** - contains `JsonArray` extension functions.
* **JsonElementEx.kt** - contains `JsonElement` extension functions. 
* **JsonObjectEx.kt** - contains `JsonObject` extension functions.
* **JsonPrimitiveEx.kt** - contains `JsonPremitive` extension functions.