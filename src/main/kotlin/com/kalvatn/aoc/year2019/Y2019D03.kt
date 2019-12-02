package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2019D03(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D03, input) {

    val lines = this.input.lines

    override suspend fun partOne(): String {
        return ""
    }

    override suspend fun partTwo(): String {
        return ""
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D03())).run()
}

