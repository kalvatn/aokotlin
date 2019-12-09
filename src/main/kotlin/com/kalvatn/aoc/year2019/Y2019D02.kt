package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking


class Y2019D02(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D02, input) {

    private val computer = IntcodeComputer(this.input.singleLineLongs())

    override suspend fun partOne(): String {
        return computer.findSolutionForVerbNounPair(12, 2).toString()
    }

    override suspend fun partTwo(): String {
        val (verb, noun) = computer.findVerbNounPairThatProducesSolution(19690720)
        return (100 * verb + noun).toString()
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D02())).run()
}

