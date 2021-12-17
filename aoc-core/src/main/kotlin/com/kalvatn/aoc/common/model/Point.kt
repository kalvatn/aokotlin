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

  fun adj4(bounds: Pair<IntRange, IntRange>? = null): Set<Point> {
    val adj = Direction.values().map {
      this + it.toPointDiff()
    }.toSet()
    return if (bounds == null) adj else {
      adj.filter {
        it.x in bounds.first && it.y in bounds.second
      }.toSet()
    }
  }

  fun adj8(bounds: Pair<IntRange, IntRange>? = null): Set<Point> {
    return adj4(bounds) + adj4Diag(bounds)
  }

  fun adj4Diag(bounds: Pair<IntRange, IntRange>? = null): Set<Point> {
    val adj = DirectionDiag.values().map {
      this + it.toPointDiff()
    }.toSet()
    return if (bounds == null) adj else {
      adj.filter {
        it.x in bounds.first && it.y in bounds.second
      }.toSet()
    }
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
