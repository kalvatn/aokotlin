package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.exceptions.Impossiburu
import java.awt.Point

class Day03 : Day {

    constructor() : super(2015, 3)
    constructor(input: PuzzleInput) : super(2015, 3, input)

    enum class Direction {
        NORTH, EAST, SOUTH, WEST;

        companion object {
            fun fromChar(char: Char): Direction {
                return when (char) {
                    '^' -> NORTH
                    '>' -> EAST
                    'v' -> SOUTH
                    '<' -> WEST
                    else -> {
                        throw Impossiburu()
                    }
                }
            }
        }
    }

    private val directions = input.singleLine().map { Direction.fromChar(it) }

    fun visit(current: Point, direction: Direction): Point {
        return when (direction) {
            Direction.NORTH -> Point(current.x, current.y - 1)
            Direction.EAST -> Point(current.x + 1, current.y)
            Direction.SOUTH -> Point(current.x, current.y + 1)
            Direction.WEST -> Point(current.x - 1, current.y)
        }
    }

    override fun partOne(): String {
        var current = Point(0, 0)
        val houses = mutableMapOf(Pair(current, 1))
        directions.forEach {
            current = visit(current, it)
            houses.putIfAbsent(current, 1)
            houses[current] = houses[current]!!.inc()
        }
        return houses.filter { it.value >= 1 }.count().toString()
    }


    override fun partTwo(): String {
        var current1 = Point(0, 0)
        var current2 = Point(0, 0)
        val houses = mutableMapOf(Pair(current1, 1))
        directions.forEachIndexed { i, it ->
            if (i % 2 == 0) {
                current1 = visit(current1, it)
                houses.putIfAbsent(current1, 1)
                houses[current1] = houses[current1]!!.inc()
            } else {
                current2 = visit(current2, it)
                houses.putIfAbsent(current2, 1)
                houses[current2] = houses[current2]!!.inc()
            }
        }
        return houses.filter { it.value >= 1 }.count().toString()
    }


}


