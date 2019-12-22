package com.kalvatn.aoc.common.model

import com.kalvatn.aoc.exceptions.Impossiburu

enum class DirectionDiag {
    NW, NE, SW, SE;

    fun toPointDiff(): Point {
        return when (this) {
            NW -> Direction.NORTH.toPointDiff() + Direction.WEST.toPointDiff()
            NE -> Direction.NORTH.toPointDiff() + Direction.EAST.toPointDiff()
            SW -> Direction.SOUTH.toPointDiff() + Direction.WEST.toPointDiff()
            SE -> Direction.SOUTH.toPointDiff() + Direction.EAST.toPointDiff()
        }
    }

}

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun toPointDiff(): Point {
        return when (this) {
            NORTH -> Point(0, -1)
            EAST -> Point(1, 0)
            SOUTH -> Point(0, 1)
            WEST -> Point(-1, 0)
        }
    }

    fun whichTurn(other: Direction): Turn {
        return when (this) {
            NORTH -> if (other == WEST) Turn.LEFT else if (other == EAST) Turn.RIGHT else error("impossiburu")
            EAST -> if (other == NORTH) Turn.LEFT else if (other == SOUTH) Turn.RIGHT else error("impossiburu")
            SOUTH -> if (other == EAST) Turn.LEFT else if (other == WEST) Turn.RIGHT else error("impossiburu")
            WEST -> if (other == SOUTH) Turn.LEFT else if (other == NORTH) Turn.RIGHT else error("impossiburu")
        }
    }


    fun turn(turn: Turn): Direction {
        return when (turn) {
            Turn.LEFT -> when (this) {
                NORTH -> WEST
                EAST -> NORTH
                SOUTH -> EAST
                WEST -> SOUTH
            }
            Turn.RIGHT -> when (this) {
                NORTH -> EAST
                EAST -> SOUTH
                SOUTH -> WEST
                WEST -> NORTH
            }
            Turn.FORWARD -> this
        }

    }

    fun toChar(): Char {
        return when (this) {
            NORTH -> '^'
            EAST -> '>'
            SOUTH -> 'v'
            WEST -> '<'
        }
    }

    fun toLong(): Long {
        return when (this) {
            NORTH -> 1
            SOUTH -> 2
            WEST -> 3
            EAST -> 4
        }
    }

    fun fromLong(value: Long): Direction {
        return when (value) {
            1L -> NORTH
            2L -> SOUTH
            3L -> WEST
            4L -> EAST
            else -> error("impossiburu")
        }
    }

    companion object {
        fun fromChar(char: Char): Direction {
            return when (char) {
                '^', 'U' -> NORTH
                '>', 'R' -> EAST
                'v', 'D' -> SOUTH
                '<', 'L' -> WEST
                else -> {
                    println(char)
                    throw Impossiburu()
                }
            }
        }

        fun fromPointDiff(p: Point): Direction {
            return when (p) {
                Point(0, -1) -> NORTH
                Point(1, 0) -> EAST
                Point(0, 1) -> SOUTH
                Point(-1, 0) -> WEST
                else -> error("impossiburu")
            }
        }
    }
}
