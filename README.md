AndroidEssentials
=================

Essential set of tools for android application development using kotlin. 

Download
--------

```groovy
implementation 'me.himanshusoni.androidessentials:recyclerview:1.0.18'

implementation 'me.himanshusoni.androidessentials:basicextensions:1.0.18'

implementation 'me.himanshusoni.androidessentials:gson-extensions:1.0.18'

```

BasicExtensions
---------------
Contains Useful and handy Kotlin Extensions for Android classes

Formatting `Date`s like pro:
```kotlin
Date().format("dd-MMM-yyyy")
```

Format `Number`s in any way you want
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

No need to implement unwanted interface methods (Thank god!!!).
```kotlin
editText.afterTextChanged { s -> }
editText.onTextChanged { s, start, before, count -> }
spinner.onItemSelected { parent, view, position, id -> }
```

Showing toast from activity and fragment is as easy as 1,2,3...
```kotlin
toast(R.string.hello_world) 
// or
toast("Hello World!")
```

Modify `Calendar`s easily. using one method only.
```kotlin
val startOfToday = Calendar.getInstance().startOfDay() // sets time to very beginning of the day i.e. 00:00:00.000
val endOfToday = Calendar.getInstance().endOfDay() // sets time to end of the day i.e. 23:59:59.999
if(calandar1.isSameDay(calendar2)) {  // also available for minute/hour/day/week/month/year and for `Date` too.
    ///... 
}
```

And many more... 

* **ActivityEx.kt** - contains Activity extension functions.
* **ContextEx.kt** - contains Context extension functions.
* **DateEx.kt** - contains Date/String/Calender extension functions. 
* **EditTextEx.kt** - contains EditText extension functions.
* **SpinnerEx.kt** - contains Spinner extension functions.
* **FragmentEx.kt** - contains Fragment extension functions.
* **NumberEx.kt** - contains Number/Int/Double extension functions.
* **StringEx.kt** - contains String extension functions.
* **Units.kt** - contains unit conversion functions.
* **ViewEx.kt** - contains View extension functions.

RecyclerView
------------
Contains base classes for recycler view adapters. Just extend one of the base Adapters and use inbuilt easy to use methods.

`BaseRecyclerViewAdapter` - for basic recycler view operations

`MultiChoiceRecyclerViewAdapter` - for multi choice recycler view adapter

`SingleChoiceRecyclerViewAdapter`- for single choice recycler view adapter

`WrapAdapter` - wrap adapter for static header and footer

`EndlessRecyclerViewScrollListener` - for endless scrolling 

GsonExtensions
--------------
[click here](gson-extensions) for details

#### Features:
 * All basic operations like add, remove items from adapter
 * Single choice and multi choice support
 * Endless scroll listener

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
