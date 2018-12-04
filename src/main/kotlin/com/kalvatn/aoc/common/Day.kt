package com.kalvatn.aoc.common

import com.kalvatn.aoc.utils.benchmark
import com.kalvatn.aoc.utils.timeit

abstract class Day(
    override val year: Int,
    override val day: Int,
    override val input: PuzzleInput = PuzzleInput.forDay(year, day)
) : IDay {

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

    operator fun invoke(i: Int, i1: Int, input: PuzzleInput) {
        this(i, i1, input)

    }
}