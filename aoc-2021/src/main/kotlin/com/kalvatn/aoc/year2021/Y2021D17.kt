package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import kotlinx.coroutines.runBlocking

class Y2021D17(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D17, input) {

  private val lines by lazy { this.input.lines }

  fun newPosVel(pos: Point, velocity: Point): Pair<Point, Point> {
    val newX = when {
      velocity.x > 0 -> velocity.x.dec()
      velocity.x < 0 -> velocity.x.inc()
      else -> 0
    }
    return Pair(
      Point(pos.x + velocity.x, pos.y + velocity.y),
      Point(newX, velocity.y.dec())
    )
  }

  fun eval(initialVelocity: Point, targetX: IntRange, targetY: IntRange): Set<Point> {
    var pos = Point(0, 0)
    var vel = initialVelocity

    val all = mutableListOf<Point>()

    var within = false
    var step = 0

    while (step < 1000) {
      val new = newPosVel(pos, vel)
      pos = new.first
      vel = new.second
      if (within && (pos.x !in targetX || pos.y !in targetY)) {
        break
      }
      all.add(pos)
      if (pos.x in targetX && pos.y in targetY) {
        within = true
      }
      step++
    }
    return all.dropLastWhile { it.x !in targetX || it.y !in targetY }.toSet()
  }

  override suspend fun partOne(): String {
//    target area: x=20..30, y=-10..-5
//    target area: x=119..176, y=-141..-84

//    val targetX = (20..30)
//    val targetY = (-10..-5)
    val targetX = (119..176)
    val targetY = (-141..-84)

    val velocities = (-1000..1000).flatMap { velX ->
      (-1000..1000).map { velY ->
        Point(velX, velY)
      }
    }.toSet()
//    val velocities = setOf(Point(7,2))
//    val velocities = setOf(Point(6,9))
//    val velocities = setOf(Point(17,-4))

    val max = velocities.mapNotNull { velocity ->
      val all = eval(velocity, targetX, targetY)
      val max = all.maxByOrNull { it.y }
      max
    }.maxByOrNull { it.y } ?: throw Impossiburu()
    return "${max.y}"
  }

  override suspend fun partTwo(): String {
    return ""
  }

  companion object {
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D17)
  PuzzleRunner.run(Y2021D17(input))
  PuzzleRunner.run(Y2021D17())
}
