package me.himanshusoni.basicextensions

fun String.mask(visibleFirst: Int, visibleLast: Int): String {
    var output = ""
    for (i in 0 until this.length) {
        if (i < visibleFirst || i >= (this.length - visibleLast)) {
            output += this[i]
        } else {
            output += "*"
        }
    }
    return output
}