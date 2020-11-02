package com.kalvatn.aoc.utils


@Suppress("unused")
inline fun <reified E> buildArray2D(numRows: Int, numColumns: Int, defaultValue: E): Array<Array<E>> {
    var rows = arrayOf<Array<E>>()
    for (y in 0 until numRows) {
        var cols = arrayOf<E>()
        for (x in 0 until numColumns) {
            cols += defaultValue
        }
        rows += cols
    }
    return rows
}

@Suppress("unused")
inline fun <reified E> buildArray2D(size: Int, defaultValue: E): Array<Array<E>> = buildArray2D(size, size, defaultValue)


@Suppress("unused")
fun intArray2D(size: Int, defaultValue: Int = 0): Array<Array<Int>> {
    return buildArray2D(size, defaultValue)
}

@Suppress("unused")
fun intArray2D(numRows: Int, numColumns: Int, defaultValue: Int = 0): Array<Array<Int>> {
    return buildArray2D(numRows, numColumns, defaultValue)
}

@Suppress("unused")
fun boolArray2D(size: Int, defaultValue: Boolean = false): Array<Array<Boolean>> {
    return buildArray2D(size, defaultValue)
}

@Suppress("unused")
fun stringArray2D(size: Int, defaultValue: String = ""): Array<Array<String>> {
    return buildArray2D(size, defaultValue)
}


@Suppress("unused")
fun <E> Array<Array<E>>.print(block: (E) -> String) {
    forEach { row ->
        row.forEach { col ->
            print(block(col))
        }
        println()
    }
}

@Suppress("unused")
fun <E> Array<Array<E>>.print(transform: Map<E, String>, default: String) {
    forEach { row ->
        row.forEach { col ->
            print(transform.getOrDefault(col, default))
        }
        println()
    }
}
