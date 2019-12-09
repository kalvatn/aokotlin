package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class Y2019D09(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D09, input) {

    val program by lazy { this.input.singleLineLongs() }
    val pc = IntcodeComputer(program)

    override suspend fun partOne(): String {
        pc.input(1)
        pc.process()
        return pc.lastOutput().toString()
    }

    override suspend fun partTwo(): String {
        pc.input(2)
        pc.process()
        return pc.lastOutput().toString()
    }

    companion object {

    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D09())).run()
}

