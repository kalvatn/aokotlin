package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.GenericPuzzle2018
import com.kalvatn.aoc.exceptions.Impossiburu

class Y2018D12(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D12, input) {


    private val initialState = this.input.lines.first().split(":")[1].trim()
    private val rules = this.input.lines.drop(1).map { it.split("=>") }.map { it[0].trim() to (it[1].trim() == "#") }.toMap()


    fun nextGeneration(current:MutableMap<Int, Boolean>):MutableMap<Int, Boolean> {
        val indexes = current.keys.map { key -> key }
        val nextPlantIndexes = mutableMapOf<Int, Boolean>()
        indexes.forEach { index ->
            var segment = ""
            (index - 2..index + 2).forEach { it ->
                nextPlantIndexes.putIfAbsent(it, false)
                segment += if (current.getOrDefault(it, false)) '#' else '.'
            }
            nextPlantIndexes[index] = rules.getOrDefault(segment, false)
        }
        return nextPlantIndexes
    }

    override suspend fun partOne(): String {

        var plantIndexes = mutableMapOf<Int, Boolean>()
        initialState.forEachIndexed { index, c ->
            plantIndexes[index] = (c == '#')
        }
        (0 - 2..initialState.length+2).forEach {
            plantIndexes.putIfAbsent(it, false)
        }

        val maxGenerations = 20
        for(generation in 1..maxGenerations) {
            val nextPlantIndexes = nextGeneration(plantIndexes)
            plantIndexes = nextPlantIndexes
        }
        return plantIndexes.filter { it.value }.keys.sum().toString()
    }

    override suspend fun partTwo(): String {
        var plantIndexes = mutableMapOf<Int, Boolean>()
        initialState.forEachIndexed { index, c ->
            plantIndexes[index] = (c == '#')
        }
        (0 - 2..initialState.length+2).forEach {
            plantIndexes.putIfAbsent(it, false)
        }

        var lastSum = 0L
        val sums = mutableMapOf<Long, Int>()
        val maxGenerations = 50000000000
        for(generation in 1..maxGenerations) {
            val nextPlantIndexes = nextGeneration(plantIndexes)
            plantIndexes = nextPlantIndexes
            val sum = plantIndexes.filter { it.value }.keys.map { it.toLong() }.sum()
            val diff = sum - lastSum

            sums[diff] = sums.getOrDefault(diff, 0).inc()
            if (sums.getOrDefault(diff, 0) > 2) {
                return (sum + (maxGenerations - generation) * diff).toString()
            }
            lastSum = sum
        }
        throw Impossiburu()
    }
}

fun main(args: Array<String>) {
//    val day = Y2018D12(PuzzleInput.forDay(Year.Y2018, Day.D12, "test"))
    val day = Y2018D12()
}