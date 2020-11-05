package com.kalvatn.aoc.year2020

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2020
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2020D01(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2020(Day.D01, input) {

    private val lines by lazy { this.input.lines }

    override suspend fun partOne(): String {
        lines.forEach {
            println(it)
        }
        return ""
    }

    override suspend fun partTwo(): String {
        return ""
    }
    
    companion object {
        
    }

}

fun main() = runBlocking {
    val input = PuzzleInput.forDay(Year.Y2020, Day.D01, "test")
    PuzzleRunner.run(Y2020D01(input))
    PuzzleRunner.run(Y2020D01())
}

