package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.IPuzzle


fun main(args: Array<String>) {
    val puzzles: Set<IPuzzle> = setOf(
//            Y2018D01(),
//            Y2018D02(),
//            Y2018D03(),
//            Y2018D04(),
            Y2018D05()
    )
    puzzles.forEach {
        println("$it")
        it.run(true)
    }
}