package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.extractIntegers
import com.marcinmoskala.math.permutations
import kotlinx.coroutines.runBlocking

class Y2019D07(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D07, input) {

    private val program by lazy { this.input.singleLine().extractIntegers() }

    private fun IntRange.maxSignal() =
            toList().permutations().map { phaseSequence ->
                val comps = phaseSequence.map { phase ->
                    IntcodeComputer(program).also { comp ->
                        comp.input(phase)
                    }
                }
                var signal = 0
                do {
                    comps.forEach {
                        it.input(signal)
                        it.process()
                        signal = it.output()
                    }
                } while (!comps.all { it.state == State.HALT })
                signal
            }.max()!!

    override suspend fun partOne(): String {
        return (0..4).maxSignal().toString()
    }

    override suspend fun partTwo(): String {
        return (5..9).maxSignal().toString()
    }
}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D07())).run()
}

