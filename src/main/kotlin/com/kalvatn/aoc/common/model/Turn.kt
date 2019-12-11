package com.kalvatn.aoc.common.model

import com.kalvatn.aoc.exceptions.Impossiburu

enum class Turn {
    LEFT, RIGHT, FORWARD;

    companion object {
        fun fromInt(value: Int) = when (value) {
            0 -> LEFT
            1 -> RIGHT
            else -> throw Impossiburu()
        }

        fun fromChar(char: Char): Turn {
            return when (char) {
                '<', 'L' -> LEFT
                '>', 'R' -> RIGHT
                else -> throw Impossiburu()
            }
        }
    }
}