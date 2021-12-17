package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt
import kotlin.math.sqrt

class Y2021D17(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D17, input) {

  private val lines by lazy { this.input.lines }

  private val regex = "target area: x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)".toRegex()
  private val xy = regex.find(lines.first())!!.destructured.toList().map { it.toInt() }

  private val targetX = (xy[0]..xy[1])
  private val targetY = (xy[2]..xy[3])

  /** https://www.reddit.com/r/adventofcode/comments/ri9kdq/2021_day_17_solutions/how2kzp/ */
  private val velocityXRange = (sqrt((targetX.first * 2).toDouble()).roundToInt()..targetX.last)
  private val velocityYRange = (targetY.first until -targetY.first)

  private val velocities = velocityXRange.flatMap { x ->
    velocityYRange.map { y ->
      Point(x, y)
    }
  }.toSet()

  private val trajectories = velocities.map { velocity ->
    velocity to validTrajectoryPoints(velocity)
  }.filter { it.second.isNotEmpty() }.toMap()

  private fun updateVelocity(velocity: Point) = velocity.copy(
    x = when {
      velocity.x > 0 -> velocity.x.dec()
      velocity.x < 0 -> velocity.x.inc()
      else -> 0
    },
    y = velocity.y.dec()
  )

  private fun validTrajectoryPoints(initialVelocity: Point) =
    generateSequence(Pair(Point(0, 0), initialVelocity)) {
      Pair(it.first + it.second, updateVelocity(it.second))
    }
      .takeWhile { it.first.x <= targetX.last && it.first.y >= targetY.first }
      .map { it.first }
      .toList()
      .dropLastWhile { it.x !in targetX || it.y !in targetY }
      .toSet()

  override suspend fun partOne(): String {
    val maxY = trajectories.values.map { points ->
      points.maxOf { it.y }
    }.maxOf { it }
    return "$maxY"
  }

  override suspend fun partTwo(): String {
    return "${trajectories.keys.size}"
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D17())
}
