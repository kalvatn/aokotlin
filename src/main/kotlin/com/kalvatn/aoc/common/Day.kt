package com.kalvatn.aoc.common

import com.kalvatn.extensions.benchmark
import com.kalvatn.extensions.timeit

abstract class Day(override val year: Int, override val day: Int) : IDay {

    override fun input(): PuzzleInput {
        return PuzzleInput.forDay(this)
    }

    override fun run(benchmark: Boolean) {
        timeit {
            println("  part one : ${partOne()}")
        }
        if (benchmark) {
            benchmark {
                partOne()
            }
        }
        timeit {
            println("  part two : ${partTwo()}")
        }
        if (benchmark) {
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