package com.kalvatn.aoc.common.model

fun Map<Point, Any>.print(colFn: (Point) -> Unit) {
  val (xMin, xMax, yMin, yMax) = with(this.keys) {
    listOf(
      minByOrNull { it.x }!!.x,
      maxByOrNull { it.x }!!.x,
      minByOrNull { it.y }!!.y,
      maxByOrNull { it.y }!!.y
    )
  }
  (yMin..yMax).forEach { y ->
    (xMin..xMax).forEach { x ->
      colFn(Point(x, y))
    }
    println()
  }
}

fun Map<Point, Any>.asString(): String {
  val (xMin, xMax, yMin, yMax) = with(this.keys) {
    listOf(
      minByOrNull { it.x }!!.x,
      maxByOrNull { it.x }!!.x,
      minByOrNull { it.y }!!.y,
      maxByOrNull { it.y }!!.y
    )
  }
  var s = ""
  (yMin..yMax).forEach { y ->
    (xMin..xMax).forEach { x ->
      s += this[Point(x, y)]
    }
    if (y != yMax) {
      s += "\n"
    }
  }
  return s
}
