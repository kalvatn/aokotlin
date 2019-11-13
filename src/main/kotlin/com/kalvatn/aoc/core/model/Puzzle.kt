package com.kalvatn.aoc.core.model

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.utils.timeit
import com.kalvatn.aoc.utils.toHMS

interface Puzzle {
    val year: Year
    val day: Day
    val input: PuzzleInput

    suspend fun partOne(): String
    suspend fun partTwo(): String

    suspend fun run(runBenchmark: Boolean = false)
}

abstract class GenericPuzzleYearDay(
        final override val year: Year,
        final override val day: Day,
        input: PuzzleInput
) : Puzzle {


    final override val input: PuzzleInput

    init {
        when (input) {
            PuzzleInput.NULL -> this.input = PuzzleInput.forDay(year, day)
            else -> this.input = input
        }
    }

    override suspend fun run(runBenchmark: Boolean) {
        val (p1Result, p1Time) = timeit { partOne() }
        val (p2Result, p2Time) = timeit { partTwo() }
        println("""
            |${toString()}  
            |   part one : ${p1Result.padEnd(20)} (${p1Time.toHMS()})
            |   part two : ${p2Result.padEnd(20)} (${p2Time.toHMS()}""".trimMargin("|"))
    }

    override fun toString(): String {
        return "${year.intString()}-${day.intString()}"
    }
}

abstract class GenericPuzzle2015(day: Day, input: PuzzleInput) : GenericPuzzleYearDay(Year.Y2015, day, input)
abstract class GenericPuzzle2018(day: Day, input: PuzzleInput) : GenericPuzzleYearDay(Year.Y2018, day, input)
