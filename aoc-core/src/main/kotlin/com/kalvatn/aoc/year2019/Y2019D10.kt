package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2019D10(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D10, input) {

  private val pointsWithAsteroids by lazy {
    this.input.lines.mapIndexed { y, line ->
      line.mapIndexed { x, value ->
        if (value == '#') Point(x, y) else null
      }.filterNotNull()
    }.flatten()
  }

  private fun visibleFrom(p: Point): Int = pointsWithAsteroids.filterNot { it == p }.map { it.angle(p) }.distinct().size

  private val countVisibleByPoint by lazy { pointsWithAsteroids.map { it to visibleFrom(it) }.toMap() }

  private val baseWithCount by lazy { countVisibleByPoint.maxByOrNull { it.value }!! }

  override suspend fun partOne(): String {
    return baseWithCount.value.toString()
  }

  override suspend fun partTwo(): String {
    val base = baseWithCount.key
    val asteroidsSortedByAngleAndDistance = pointsWithAsteroids.filterNot { it == base }
      .groupBy { base.angle(it) }
      .map { (angle, points) ->
        angle to points.sortedBy { base.distance(it) }.toMutableList()
      }
      .sortedByDescending { it.first }.toMap().values.toList()

    val twoHundredth = (0 until 200).fold(Point(0, 0)) { acc, number ->
      val pointsRemainingAtAngle = asteroidsSortedByAngleAndDistance[number % asteroidsSortedByAngleAndDistance.size]
      return@fold if (pointsRemainingAtAngle.isNotEmpty()) pointsRemainingAtAngle.removeAt(0) else acc
    }

    return (twoHundredth.x * 100 + twoHundredth.y).toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D10())).run()
}
