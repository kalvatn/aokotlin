package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.common.model.Point

class Y2018D11(input: PuzzleInput? = null) : APuzzle2018(Day.D11, input) {


    private val serialNumber: Int = this.input.singleLine().toInt()


    data class FuelCell(val point: Point, val serial: Int) {
        fun powerLevel(): Int {
            val rackId = point.x + 10
            val powerLevel = (rackId * point.y + serial) * rackId
            val powerString = powerLevel.toString()
            var hundreds = 0
            if (powerString.length >= 3) {
                hundreds = powerString.substring(powerString.length - 3)[0].toString().toInt()
            }
            return hundreds - 5
        }

    }

    private val powerLevels = calculatePowerLevels()

    private fun calculatePowerLevels(): MutableMap<Point, Int> {
        val powerLevels = mutableMapOf<Point, Int>()
        (0..300).forEach { x ->
            (0..300).forEach { y ->
                val fuelCell = FuelCell(Point(x, y), serialNumber)
                powerLevels[fuelCell.point] = fuelCell.powerLevel()
            }
        }
        return powerLevels
    }

    override fun partOne(): String {
        val totalPower = mutableMapOf<Point, Int>()
        for (entry in powerLevels.keys) {
            if (entry.x + 3 > 300) {
                continue
            }
            if (entry.y + 3 > 300) {
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


    override fun partTwo(): String {
        val maxSize = 300
        val powerGrid = mutableSetOf<PowerGrid>()
        for (entry in powerLevels.keys) {
            println("$entry")
            for (size in maxSize downTo 1) {
                if (entry.x + size > 300) {
                    continue
                }
                if (entry.y + size > 300) {
                    continue
                }
                val grid = entry.gridFrom(size)
                powerGrid.add(PowerGrid(entry, size, grid.map { powerLevels[it]!! }.sum()))
            }
        }
        val maxPower = powerGrid.maxBy { it.power }!!
        println(maxPower)
        return "${maxPower.topleft.x},${maxPower.topleft.y},${maxPower.size}"
    }
}

fun main(args: Array<String>) {
//    val day = Y2018D11(PuzzleInput.ofSingleLine("42"))
    val day = Y2018D11()
    day.run()
}