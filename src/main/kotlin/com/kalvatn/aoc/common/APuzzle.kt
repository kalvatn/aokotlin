package com.kalvatn.aoc.common

import com.kalvatn.aoc.utils.benchmark
import com.kalvatn.aoc.utils.timeit

abstract class APuzzle(final override val year: Year, final override val day: Day, input: PuzzleInput?) : IPuzzle {


    final override val input: PuzzleInput

    init {
        when (input) {
            null -> this.input = PuzzleInput.forDay(year, day)
            else -> this.input = input
        }
    }

    override fun run(runBenchmark: Boolean) {
        timeit {
            println("  part one : ${partOne()}")
        }
        if (runBenchmark) {
            benchmark {
                partOne()
            }
        }
        timeit {
            println("  part two : ${partTwo()}")
        }
        if (runBenchmark) {
            benchmark {
                partTwo()
            }
        }
        println()
    }

    override fun toString(): String {
        return "$year-${day.toString().padStart(2, '0')}"
    }
}