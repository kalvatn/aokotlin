package com.kalvatn.aoc.common.model

import com.kalvatn.aoc.exceptions.Impossiburu

enum class Turn {
    LEFT, RIGHT, FORWARD;

    companion object {
        fun fromChar(char: Char): Turn {
            return when (char) {
                '>', 'R' -> RIGHT
                '<', 'L' -> LEFT
                else -> throw Impossiburu()
            }
        }
    }
}