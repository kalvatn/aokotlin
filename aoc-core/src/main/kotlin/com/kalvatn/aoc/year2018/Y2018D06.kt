package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2018
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import java.lang.Math.abs

class Y2018D06(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D06, input) {

  private val lol = this.input.lines.map {
    it.split(",")
      .map { it.trim().toInt() }
  }.map { Point(it.first(), it[1]) }

  fun dist(node: Point, goal: Point): Int {
    return abs(goal.x - node.x) + abs(goal.y - node.y)
  }

  fun mapDist(p1: Point, points: Iterable<Point>): Map<Int, MutableSet<Point>> {
    val map = mutableMapOf<Int, MutableSet<Point>>()

    points.forEach { it ->
      val dist = dist(p1, it)
      map.putIfAbsent(dist, mutableSetOf())
      map[dist]!!.add(it)
    }
    return map
  }

  data class Point(val x: Int, val y: Int)

  data class PointValue(val point: Point, val distance: Int, val takenByNode: Point) {

    companion object {
      val NO_NODE = Point(-1, -1)
    }
  }

  override suspend fun partOne(): String {
    val xMax = lol.map { it.x }.max()!!
    val yMax = lol.map { it.y }.max()!!

    val pts = (0..xMax).map { x ->
      (0..yMax).map { y ->
        Point(x, y)
      }
    }.flatten()

    val mapOf = mutableMapOf<Point, Map<Int, MutableSet<Point>>>()
    for (p1 in lol) {
      mapOf[p1] = mapDist(p1, pts)
    }
    val maxBy = mapOf.map { it.value.keys }.flatten().max()

    val pointValues: MutableMap<Point, PointValue> = mutableMapOf()

    (0..maxBy!!).forEach { dist ->
      mapOf.forEach { it ->
        it.value[dist]?.forEach { point ->
          val pointValue = pointValues[point]
          if (pointValue == null) {
            pointValues[point] = PointValue(point, dist, it.key)
          } else {
            if (pointValue.distance == dist && pointValue.takenByNode != PointValue.NO_NODE) {
              pointValues[point] = PointValue(point, dist, PointValue.NO_NODE)
            }
          }
        }
      }
    }
    val byNode = mutableMapOf<Point, MutableSet<Point>>()
    pointValues.forEach { it ->
      val value = it.value
      byNode.putIfAbsent(value.takenByNode, mutableSetOf())
      byNode[value.takenByNode]!!.add(value.point)
    }
    var maxArea = 0
    for (mutableEntry in byNode) {
      if (!isInfinite(mutableEntry.key, pointValues)) {
        if (mutableEntry.value.size > maxArea) {
          maxArea = mutableEntry.value.size
        }
      }
    }

    return maxArea.toString()
  }

  override suspend fun partTwo(): String {
    val xMax = lol.maxBy { it.x }!!.x
    val yMax = lol.maxBy { it.y }!!.y

    var area = 0L
    for (x in 0..xMax) {
      for (y in 0..yMax) {
        if (lol.map { dist(it, Point(x, y)) }.sum() < 10000) {
          area++
        }
      }
    }

    return area.toString()
  }

  fun surrounding(point: Point): List<Point> {
    return listOf(
      Point(point.x.dec(), point.y),
      Point(point.x.dec(), point.y.inc()),
      Point(point.x.dec(), point.y.dec()),

      Point(point.x.inc(), point.y),
      Point(point.x.inc(), point.y.inc()),
      Point(point.x.inc(), point.y.dec()),

      Point(point.x, point.y.inc()),
      Point(point.x, point.y.dec()),
      Point(point.x, point.y)
    )
  }

  fun isInfinite(node: Point, values: Map<Point, PointValue>): Boolean {
    if (node == PointValue.NO_NODE) {
      return true
    }
    val maxRight = values.map { it.value.point.x }.max()!!
    val maxDown = values.map { it.value.point.y }.max()!!
    val left = (0..node.x).map { Point(node.x - it, node.y) }.filter { values.containsKey(it) }
    val right = (0..maxRight).map { Point(node.x + it, node.y) }.filter { values.containsKey(it) }
    val up = (0..node.y).map { Point(node.x, node.y - it) }.filter { values.containsKey(it) }
    val down = (0..maxDown).map { Point(node.x, node.y + it) }.filter { values.containsKey(it) }

    val infiniteLeft = left.all {
      values[it]!!.takenByNode == node
    }
    val infiniteRight = right.all {
      values[it]!!.takenByNode == node
    }
    val infiniteUp = up.all {
      values[it]!!.takenByNode == node
    }
    val infiniteDown = down.all {
      values[it]!!.takenByNode == node
    }

    return infiniteLeft || infiniteRight || infiniteUp || infiniteDown
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2018D06())).run()
}
