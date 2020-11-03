package com.kalvatn.aoc.common.model

import kotlin.math.abs
import kotlin.math.atan2

data class Point(val x: Int, val y: Int) : Comparable<Point> {
  override fun compareTo(other: Point): Int {
    if (this.y == other.y) {
      return this.x.compareTo(other.x)
    }
    return this.y.compareTo(other.y)
  }

  fun adj4(): Set<Point> {
    return Direction.values().map {
      this + it.toPointDiff()
    }.toSet()
  }

  fun adj8(): Set<Point> {
    return adj4() + adj4Diag()
  }

  fun adj4Diag(): Set<Point> {
    return DirectionDiag.values().map {
      this + it.toPointDiff()
    }.toSet()
  }

  fun surrounding(): Set<Point> {
    return setOf(
      Point(x.dec(), y),
      Point(x.dec(), y.inc()),
      Point(x.dec(), y.dec()),

      Point(x.inc(), y),
      Point(x.inc(), y.inc()),
      Point(x.inc(), y.dec()),

      Point(x, y.inc()),
      Point(x, y.dec())
    )
  }

  operator fun plus(other: Point): Point {
    return Point(this.x + other.x, this.y + other.y)
  }

  operator fun minus(other: Point): Point {
    return Point(this.x - other.x, this.y - other.y)
  }

  operator fun times(n: Int): Point {
    return Point(this.x * n, this.y * n)
  }

  fun distance(other: Point): Int {
    return abs(other.x - this.x) + abs(other.y - this.y)
  }

  fun angle(other: Point): Double {
    return atan2((other.x - x).toDouble(), (other.y - y).toDouble())
  }

  fun gridFrom(size: Int): Set<Point> {
    return (0 until size).map { x ->
      (0 until size).map { y ->
        Point(this.x + x, this.y + y)
      }
    }.flatten().toSet()
  }
}

fun Map<Point, Any>.print(colFn: (Point) -> Unit) {
  val (xMin, xMax, yMin, yMax) = with(this.keys) {
    listOf(
      minBy { it.x }!!.x,
      maxBy { it.x }!!.x,
      minBy { it.y }!!.y,
      maxBy { it.y }!!.y
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
      minBy { it.x }!!.x,
      maxBy { it.x }!!.x,
      minBy { it.y }!!.y,
      maxBy { it.y }!!.y
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
