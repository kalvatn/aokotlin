package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.graph.BFS
import com.kalvatn.aoc.common.graph.DFS
import com.kalvatn.aoc.common.graph.Graph
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import kotlin.math.abs


class Y2019D06(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D06, input) {

    val connections = this.input.lines.also { println(it) }.map {
        val split = it.split(")")
        val first = split.first()
        val second = split.last()
        Pair(first, second)
    }

    fun getOrbits(graph: Graph<String>, from: String, root: String): Set<String> {
        val set = mutableSetOf<String>()
        var current = from
        while (true) {
            if (current == root) {
                return set.toSet()
            }
            val parent = graph.parents[current]!!.first()
            if (parent !in set && parent != from) {
                set.add(parent)
                current = parent
            } else {
                return set
            }
        }
    }

    override suspend fun partOne(): String {
        val graph = Graph<String>()
        val root = "COM"
        for (connection in connections) {
            graph.connect(connection.first, connection.second)
        }
        var total = 0
        for (c in graph.connections.keys) {
            val orbits = getOrbits(graph, c, root)
//            println("$c -> $orbits ${orbits.size}")
            total += orbits.size
        }
        val you = "YOU"
        val san = "SAN"
        val parentOfSan = graph.parents[san]!!.first()
        val bfs = graph.BFS(you, parentOfSan)
        println(bfs)

        return total.toString()
    }

    override suspend fun partTwo(): String {
        return ""
    }

}

fun main() = runBlocking {
    //        PuzzleRunner(listOf(Y2019D06(PuzzleInput.Companion.forDay(Year.Y2019, Day.D06, "test")))).run()
//    PuzzleRunner(listOf(Y2019D06(PuzzleInput.Companion.forDay(Year.Y2019, Day.D06, "test2")))).run()
    PuzzleRunner(listOf(Y2019D06())).run()
    // 493 too high
}

