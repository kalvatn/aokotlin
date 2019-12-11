package com.kalvatn.aoc.common.model

import com.kalvatn.aoc.exceptions.Impossiburu

enum class Turn {
    LEFT, RIGHT, FORWARD;

    companion object {
        fun fromChar(char: Char): Turn {
            return when (char) {
                '>', 'R', '1' -> RIGHT
                '<', 'L', '0' -> LEFT
                else -> throw Impossiburu()
            }
        }
    }
}