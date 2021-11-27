package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.geometry.Axis
import com.kalvatn.aoc.common.geometry.Vec3
import com.kalvatn.aoc.common.geometry.fromComparison
import com.kalvatn.aoc.common.geometry.plus
import com.kalvatn.aoc.common.math.lcm
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

class Y2019D12(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D12, input) {

  private val moons by lazy { this.input.lines.map { Moon.fromString(it) } }

  data class Moon(
    var position: Vec3,
    var velocity: Vec3
  ) {
    companion object {
      fun fromString(string: String): Moon {
        val (x, y, z) = string.split(",").flatMap { it.extractIntegers() }
        return Moon(Vec3(x, y, z), Vec3(0, 0, 0))
      }
    }

    private fun potentialEnergy(): Int = abs(position.x) + abs(position.y) + abs(position.z)
    private fun kineticEnergy(): Int = abs(velocity.x) + abs(velocity.y) + abs(velocity.z)
    fun totalEnergy() = potentialEnergy() * kineticEnergy()

    fun move() {
      position += velocity
    }

    fun calcNewVelocity(other: Moon): Vec3 {
      return other.position.fromComparison(this.position)
    }
  }

  private fun List<Moon>.allOnAxis(axis: Axis): List<Int> {
    return when (axis) {
      Axis.X -> map { it.position.x } + map { it.velocity.x }
      Axis.Y -> map { it.position.y } + map { it.velocity.y }
      Axis.Z -> map { it.position.z } + map { it.velocity.z }
    }
  }

  private fun List<Moon>.move() {
    val newVelocities = this.associateWith { moon ->
      moon.velocity + this.filterNot { it == moon }.map { moon.calcNewVelocity(it) }.let { v ->
        Vec3(
          v.sumOf { it.x },
          v.sumOf { it.y },
          v.sumOf { it.z }
        )
      }
    }

    this.forEach {
      it.velocity = newVelocities[it] ?: error("impossiburu")
      it.move()
    }
  }

  override suspend fun partOne(): String {
    repeat(1000) {
      moons.move()
    }
    return moons.sumOf { it.totalEnergy() }.toString()
  }

  override suspend fun partTwo(): String {
    val initial = moons.map { it.copy() }

    val targetX = initial.allOnAxis(Axis.X)
    val targetY = initial.allOnAxis(Axis.Y)
    val targetZ = initial.allOnAxis(Axis.Z)

    val repeats = mutableListOf<Long>(0, 0, 0)
    var steps = 0L
    while (repeats.any { it == 0L }) {
      moons.move()

      steps++
      if (repeats[0] == 0L && targetX == moons.allOnAxis(Axis.X)) {
        repeats[0] = steps
      }
      if (repeats[1] == 0L && targetY == moons.allOnAxis(Axis.Y)) {
        repeats[1] = steps
      }
      if (repeats[2] == 0L && targetZ == moons.allOnAxis(Axis.Z)) {
        repeats[2] = steps
      }
    }
    return repeats.lcm().toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D12())).run()
}
