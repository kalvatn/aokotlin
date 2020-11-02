package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.graph.WeightedGraph
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2015D09(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D09, input) {

    val graph by lazy {
        val g = WeightedGraph<String>()
        val r = "^(\\w+) to (\\w+) = (\\d+)".toRegex()
        this.input.lines.forEach {
            val (a, b, d) = r.matchEntire(it)!!.destructured
            g.connect(a, b, d.toInt())
        }
        g
    }

    override suspend fun partOne(): String {
        return graph.edges.keys.map {
            graph.shortestVisitAll(it, false).distance
        }.min().toString()
    }

    override suspend fun partTwo(): String {
        return graph.edges.keys.map {
            graph.longestVisitAll(it, false).distance
        }.max().toString()
    }
    
    companion object {
        
    }

}

fun main() = runBlocking {
//    val input = PuzzleInput.forDay(Year.Y2015, Day.D09, "test")
//    PuzzleRunner.run(Y2015D09(input))
    PuzzleRunner(listOf(Y2015D09())).run()
}

