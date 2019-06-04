AndroidEssentials
=================

Essential set of tools for android application development using kotlin. 

Download
--------

```groovy
implementation 'me.himanshusoni.androidessentials:recyclerview:1.0.4'

implementation 'me.himanshusoni.androidessentials:basicextensions:1.0.4'

```

BasicExtensions
---------------
Contains Useful and handy Kotlin Extensions for Android classes

Formatting dates like pro:
```kotlin
Date().format("dd-MMM-yyyy")
```

Format numbers in any way you want
```kotlin
val number1 = 199226.0805
val number2 = 100
number1.format(scale = 2, grouping = false, forceScale = true) // 199226.08
number1.format(scale = 2, grouping = true, forceScale = true) // 199,226.08
number2.format(scale = 2, grouping = false, forceScale = true) // 100.0
number2.format(scale = 2, grouping = false, forceScale = false) // 100.00
```

Converting different android size units was never so easy before
```kotlin
12.dpToPx().toInt() // gives you pixel equivalent of 12dp in Int
12.dpToPx().toFloat() // gives you pixel equivalent of 12dp in Float
```

Showing toast from activity and fragment is as easy as 1,2,3...
```kotlin
toast(R.string.hello_world) 
// or
toast("Hello World!")
```

And many more... 

* **ActivityEx.kt** - contains Activity extension functions.
* **ContextEx.kt** - contains Context extension functions.
* **DateEx.kt** - contains Date/String/Calender extension functions. 
* **EditTextEx.kt** - contains EditText extension functions.
* **FragmentEx.kt** - contains Fragment extension functions.
* **NumberEx.kt** - contains Number/Int/Double extension functions.
* **StringEx.kt** - contains String extension functions.
* **Units.kt** - contains unit conversion functions.
* **ViewEx.kt** - contains View extension functions.


RecyclerView
------------
Contains base classes for recycler view adapters. Just extend one of the base Adapters and use inbuilt easy to use methods

#### Features:
 * All basic operations like add, remove items from adapter
 * Single choice and multi choice support

> developed to make programming easy.
>
> by Himanshu Soni (himanshusoni.me@gmail.com)


License
--------

    Copyright 2013 Square, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific
