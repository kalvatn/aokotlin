package com.kalvatn.aoc.core.runner

import com.kalvatn.aoc.core.model.Puzzle
import com.kalvatn.aoc.utils.timeit
import com.kalvatn.aoc.utils.toHMS
import com.kalvatn.aoc.year2015.*
import com.kalvatn.aoc.year2018.*
import com.kalvatn.aoc.year2019.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class PuzzleRunner(private val puzzles: List<Puzzle> = listOf()) {
    suspend fun run() {
        timeit {
            puzzles.map { GlobalScope.async { it.run() } }.awaitAll()
        }.let {
            println()
            println()
            println("${puzzles.size} puzzles finished in ${it.second.toHMS()}")
        }
    }
}

fun main() = runBlocking {
    val puzzles = listOf(
            Y2015D01(),
            Y2015D02(),
            Y2015D03(),
            Y2015D04(),
            Y2015D05(),
            Y2015D06(),
            Y2015D07(),

            Y2018D01(),
            Y2018D02(),
            Y2018D03(),
            Y2018D04(),
            Y2018D05(),
            Y2018D06(),
            Y2018D07(),
            Y2018D08(),
            Y2018D09(),
            Y2018D10(),
            Y2018D11(),
            Y2018D12(),
            Y2018D13(),
            Y2018D14(),
            Y2018D15(),
            Y2018D16(),

            Y2019D01(),
            Y2019D02(),
            Y2019D03(),
            Y2019D04()
    )
    PuzzleRunner(puzzles).run()
}