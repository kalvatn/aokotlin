package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.IPuzzle
import com.kalvatn.aoc.utils.timeit


fun main(args: Array<String>) {
    timeit {
        val puzzles: Set<IPuzzle> = setOf(
                Y2015D01(),
                Y2015D02(),
                Y2015D03(),
                Y2015D04(),
                Y2015D05()
        )
        puzzles.forEach {
            println("$it")
            it.run(true)
        }
    }
}