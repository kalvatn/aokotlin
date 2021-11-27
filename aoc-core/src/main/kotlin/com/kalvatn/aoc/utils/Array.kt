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
inline fun <reified E> buildArray2D(size: Int, defaultValue: E): Array<Array<E>> =
  buildArray2D(size, size, defaultValue)

@Suppress("unused")
fun intArray2D(size: Int, defaultValue: Int = 0): Array<IntArray> {
  return intArray2D(size, size, defaultValue)
}

@Suppress("unused")
fun intArray2D(numRows: Int, numColumns: Int, defaultValue: Int = 0): Array<IntArray> {
  var rows = arrayOf<IntArray>()
  for (y in 0 until numRows) {
    val cols = IntArray(numColumns)
    for (x in 0 until numColumns) {
      cols[x] = defaultValue
    }
    rows += cols
  }
  return rows
}

@Suppress("unused")
fun boolArray2D(size: Int, defaultValue: Boolean = false): Array<BooleanArray> {
  var rows = arrayOf<BooleanArray>()
  for (y in 0 until size) {
    val cols = BooleanArray(size)
    for (x in 0 until size) {
      cols[x] = defaultValue
    }
    rows += cols
  }
  return rows
}

@Suppress("unused")
fun stringArray2D(size: Int, defaultValue: String = ""): Array<Array<String>> {
  return buildArray2D(size, defaultValue)
}

@Suppress("unused")
fun Array<IntArray>.print(block: (Int) -> String) {
  forEach { row ->
    row.forEach { col ->
      print(block(col))
    }
    println()
  }
}
fun Array<IntArray>.flatten(): List<Int> {
  val result = ArrayList<Int>(sumOf { it.size })
  for (row in this) {
    for (col in row) {
      result.add(col)
    }
  }
  return result
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
