package com.kalvatn.aoc.common.model


data class Point(val x: Int, val y: Int):Comparable<Point> {
    override fun compareTo(other: Point): Int {
        if (this.y == other.y) {
            return this.x.compareTo(other.x)
        }
        return this.y.compareTo(other.y)
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

    fun plus(other: Point): Point {
       return Point(this.x + other.x, this.y + other.y)
    }

    fun gridFrom(size: Int): Set<Point> {
        return (0 until size).map {x ->
            (0 until size).map { y ->
                Point(this.x + x, this.y + y)
            }
        }.flatten().toSet()
    }
}
