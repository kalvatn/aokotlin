package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2018
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class Y2018D11(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D11, input) {

  private val serialNumber: Int = this.input.singleLine().toInt()

  private val TOTAL_GRID_SIZE: Int = 300

  data class FuelCell(val point: Point, val serial: Int) {

    private fun getHundredsDigit(integer: Int): Int {
      return when {
        integer < 100 -> 0
        integer == 100 -> 1
        else -> {
          val powerString = integer.toString()
          powerString.substring(powerString.length - 3)[0].toString().toInt()
        }
      }
    }

    fun powerLevel(): Int {
      val rackId = point.x + 10
      val powerLevel = (rackId * point.y + serial) * rackId
      return getHundredsDigit(powerLevel) - 5
    }
  }

  private val powerLevels = calculatePowerLevels()

  private fun calculatePowerLevels(): MutableMap<Point, Int> {
    val powerLevels = mutableMapOf<Point, Int>()
    (0..TOTAL_GRID_SIZE).forEach { x ->
      (0..TOTAL_GRID_SIZE).forEach { y ->
        val fuelCell = FuelCell(Point(x, y), serialNumber)
        powerLevels[fuelCell.point] = fuelCell.powerLevel()
      }
    }
    return powerLevels
  }

  override suspend fun partOne(): String {
    val totalPower = mutableMapOf<Point, Int>()
    for (entry in powerLevels.keys) {
      if (entry.x + 3 > TOTAL_GRID_SIZE) {
        continue
      }
      if (entry.y + 3 > TOTAL_GRID_SIZE) {
        continue
      }
      val center = Point(entry.x + 1, entry.y + 1)
      val region = (center.surrounding() + center)
      if (region.all { powerLevels.containsKey(it) }) {
        totalPower[entry] = region.map { powerLevels[it]!! }.sum()
      }
    }
    val maxPowerPoint = totalPower.maxBy { it.value }!!.key
    return "${maxPowerPoint.x},${maxPowerPoint.y}"
  }

  data class PowerGrid(val topleft: Point, val size: Int, val power: Int)

  private suspend fun createPowerGridFromPoint(point: Point, minSize: Int, maxSize: Int): Set<PowerGrid> {
    return (minSize..maxSize)
      .asSequence()
      .filter { point.x + it <= TOTAL_GRID_SIZE }
      .filter { point.y + it <= TOTAL_GRID_SIZE }
      .toList()
      .map {
        GlobalScope.async {
          PowerGrid(point, it, point.gridFrom(it).map { point -> powerLevels[point]!! }.sum())
        }
      }.awaitAll()
      .toSet()
  }

  override suspend fun partTwo(): String {
    val minSize = 8
    val maxSize = 16
    val tasks = powerLevels.keys.map {
      GlobalScope.async {
        //                println("$it")
        createPowerGridFromPoint(it, minSize, maxSize)
      }
    }

    val grids = tasks.awaitAll().flatten()
    val gridWithMaxPower = grids.maxBy { it.power }!!
    return "${gridWithMaxPower.topleft.x},${gridWithMaxPower.topleft.y},${gridWithMaxPower.size}"
  }
}

fun main() = runBlocking {
  Y2018D11().run()
}
