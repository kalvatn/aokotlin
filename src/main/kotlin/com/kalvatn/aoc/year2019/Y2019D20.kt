package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.print
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2019D20(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D20, input) {

    val lines by lazy { this.input.lines }

    private val maze by lazy { parseMaze() }

    fun parseMaze():Map<Point, Char> {
        val map = mutableMapOf<Point,Char>()
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, item ->
                val p = Point(x, y)
                map[p] = item
            }
        }
        return map
    }

    data class Portal(val name:String, val entrance:Point)

    fun portals():Map<String, Set<Portal>> {
        val portals = mutableMapOf<String, MutableSet<Portal>>()
        maze.filterValues { it in ('A'..'Z') }.forEach { (k, v) ->
            val adj = k.adj4().filter { maze[it] in ('A'..'Z') || maze[it] == '.' }
            if (adj.size == 2) {
                val (p1, p2) = adj
                val (name, entrance) = if (maze[p1] in ('A'..'Z')) {
                    Pair(("$v" + maze[p1]), p2)
                } else {
                    Pair("$v" + maze[p2], p1)
                }
                val nameSorted = name.toList().sorted().joinToString("")
                portals.computeIfAbsent(nameSorted) { mutableSetOf() }.add(Portal(nameSorted, entrance))
            }
        }
        return portals
    }

    override suspend fun partOne(): String {
        maze.print {
            print(maze[it])
        }
        portals().forEach {
            println(it)
        }

        return ""
    }

    override suspend fun partTwo(): String {
        return ""
    }

}

fun main() = runBlocking {
//    val input = PuzzleInput.forDay(Year.Y2019, Day.D20, "test")
    val input = PuzzleInput.forDay(Year.Y2019, Day.D20, "test2")
    PuzzleRunner(listOf(Y2019D20(input))).run()
//    PuzzleRunner(listOf(Y2019D20())).run()
}

