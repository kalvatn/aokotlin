package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.print
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import java.util.*


class Y2019D18(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D18, input) {

    private val map by lazy { this.input.asPoints() }


    data class Node(
            val point: Point,
            val keys: Set<Char>,
            val distance: Int,
            val path: Set<Point>
    ) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            val d = distance.compareTo(other.distance)
            if (d == 0) {
                //                println("distance equal $distance, $keys vs ${other.keys} $k")
                return other.keys.size.compareTo(keys.size)
            }
            return d
        }

        override fun toString(): String {
            return "(${point.x}, ${point.y}) keys=$keys (${keys.size}) distance=$distance"
        }
    }

    override suspend fun partOne(): String {
        map.print {
            print(map[it])
        }

        val start = map.filterValues { it == '@' }.keys.first()
        val allKeys = map.filterValues { it in 'a'..'z' }.values.toSet()
        val allDoors = map.filterValues { it in 'A'..'Z' }.values.toSet()

        val queue = PriorityQueue<Node>()
        var best = Long.MAX_VALUE
        var steps = 0L

        queue.add(Node(
                point = start,
                keys = setOf(),
                distance = 0,
                path = setOf()
        ))

        val seen = mutableSetOf<Int>()
        while (queue.isNotEmpty()) {
            val current = queue.remove()

            if (current.distance > best) {
                continue
            }

            val key = Objects.hash(current.point, current.path, current.keys.sorted())
            if (key in seen) {
//                println("$key already seen")
                continue
            }
            seen.add(key)
            if (steps % 100000L == 0L) {
                println("$steps $current")
            }
            val keys = current.keys.toMutableSet()
            val c = map[current.point] ?: error("impossiburu")
            if (c in allKeys) {
                keys.add(c)
            } else if (c in allDoors && c.toLowerCase() !in keys) {
                continue
            }

            val adj = current.point.adj4().filter { it in map.keys }.filter { map[it] != '#' }
            if (keys == allKeys) {
                if (current.distance <= best) {
                    best = current.distance.toLong()
                }
                println("FOUND $keys in $best steps")
            }
            adj.forEach {
                queue.add(Node(point = it,
                        keys = keys,
                        distance = current.distance + 1,
                        path = current.path + it
                )
                )
            }
            steps++
        }
        return best.toString()
    }

    override suspend fun partTwo(): String {
        return ""
    }

    companion object {

    }

}

fun main() = runBlocking {
    //    PuzzleRunner(listOf(Y2019D18(PuzzleInput.forDay(Year.Y2019, Day.D18, "test2")))).run()
    PuzzleRunner(listOf(Y2019D18())).run()
}

