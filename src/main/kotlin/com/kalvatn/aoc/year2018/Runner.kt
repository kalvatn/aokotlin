package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.IPuzzle


fun main(args: Array<String>) {
    val puzzles: Set<IPuzzle> = setOf(
            Y2018D01(),
            Y2018D02(),
            Y2018D03(),
            Y2018D04(),
            Y2018D05(),
            Y2018D06(),
            Y2018D07(),
            Y2018D08(),
            Y2018D09(),
            Y2018D10()
//            Y2018D11(),
//            Y2018D12(),
//            Y2018D13(),
//            Y2018D14(),
//            Y2018D15(),
//            Y2018D16(),
//            Y2018D17(),
//            Y2018D18(),
//            Y2018D19(),
//            Y2018D20(),
//            Y2018D21(),
//            Y2018D22(),
//            Y2018D23(),
//            Y2018D24(),
//            Y2018D25()
    )
    puzzles.forEach {
        println("$it")
        it.run()
    }
}