package com.kalvatn.aoc.common.model

import com.kalvatn.aoc.exceptions.Impossiburu

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun toPointDiff():Point {
        return when(this) {
            NORTH -> Point(0, -1)
            EAST -> Point(1, 0)
            SOUTH -> Point(0, 1)
            WEST -> Point(-1, 0)
        }
    }
    fun alias():String {
        return when(this) {

            NORTH -> "UP"
            EAST -> "RIGHT"
            SOUTH -> "DOWN"
            WEST -> "LEFT"
        }
    }

    fun turn(turn:Turn):Direction {
        return when(turn) {
            Turn.LEFT -> when(this) {
                NORTH -> WEST
                EAST -> NORTH
                SOUTH -> EAST
                WEST -> SOUTH
            }
            Turn.RIGHT -> when(this) {
                NORTH -> EAST
                EAST -> SOUTH
                SOUTH -> WEST
                WEST -> NORTH
            }
            Turn.FORWARD -> this
        }

    }

    fun toChar(): Char {
        return when(this) {
            NORTH -> '^'
            EAST -> '>'
            SOUTH -> 'v'
            WEST -> '<'
        }
    }

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
