package com.kalvatn.aoc.common.model


data class Point(var x: Int, var y: Int) {
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

    fun gridFrom(size: Int): Set<Point> {
        val points = mutableSetOf<Point>()
        (0 until size).forEach {x ->
            (0 until size).forEach { y ->
                points.add(Point(this.x + x, this.y + y))
            }
        }
        return points
    }
}
