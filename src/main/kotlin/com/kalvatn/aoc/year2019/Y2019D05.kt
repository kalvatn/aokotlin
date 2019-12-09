package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking


class Y2019D05(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D05, input) {

    private val program by lazy { this.input.singleLineLongs() }

    override suspend fun partOne(): String {
        return IntcodeComputer(program).runDiagnostic(1).toString()
    }

    override suspend fun partTwo(): String {
        return IntcodeComputer(program).runDiagnostic(5).toString()
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D05())).run()
}

