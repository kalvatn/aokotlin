package com.kalvatn.aoc.utils


inline fun <reified E> buildArray2D(size: Int, defaultValue: E): Array<Array<E>> {
    var rows = arrayOf<Array<E>>()
    for (i in 0 until size) {
        var cols = arrayOf<E>()
        for (j in 0 until size) {
            cols += defaultValue
        }
        rows += cols
    }
    return rows
}

@Suppress("unused")
fun intArray2D(size: Int, defaultValue: Int = 0): Array<Array<Int>> {
    return buildArray2D(size, defaultValue)
}

@Suppress("unused")
fun boolArray2D(size: Int, defaultValue: Boolean = false): Array<Array<Boolean>> {
    return buildArray2D(size, defaultValue)
}

@Suppress("unused")
fun stringArray2D(size: Int, defaultValue: String = ""): Array<Array<String>> {
    return buildArray2D(size, defaultValue)
}
