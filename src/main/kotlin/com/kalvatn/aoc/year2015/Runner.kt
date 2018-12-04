package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.IDay


fun main(args: Array<String>) {
    val days: Set<IDay> = setOf(
            Day01(),
            Day02(),
            Day03(),
            Day04()
    )
    days.forEach {
        println("$it")
        it.run(true)
    }
}