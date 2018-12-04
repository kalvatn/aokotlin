package com.kalvatn.aoc.common

import java.io.File

@Suppress("unused")
class PuzzleInput(val lines: List<String>) {

    fun <E> map(fn: (String) -> E): List<E> {
        return lines.map { fn(it) }
    }

    fun singleLine(): String {
        return lines.first()
    }

    fun asIntegers(): List<Int> {
        return map { it.toInt() }
    }

    fun asInteger(): Int {
        return asIntegers().first()
    }


    companion object {
        @JvmStatic
        fun forDay(puzzle: IPuzzle, suffix: String = ""): PuzzleInput {
            return forDay(puzzle.year, puzzle.day, suffix)
        }

        @JvmStatic
        fun forDay(year: Year, day: Day, suffix: String = ""): PuzzleInput {

            val dayStr = day.toString().substring(1)
            val yearStr = year.toString().substring(1)
            val extra = if (!suffix.isBlank()) "_$suffix" else ""
            val filename = "src/main/resources/inputs/$yearStr/$dayStr$extra"

            val file = File(filename)
            return PuzzleInput(file.readLines().filter { !it.isBlank() })
        }

        @JvmStatic
        fun ofCommaDelimited(string: String): PuzzleInput {
            return ofSingleLine(string, splitOn = ",")
        }

        @JvmStatic
        fun ofSingleLine(string: String, splitOn: String = ""): PuzzleInput {
            return PuzzleInput(string.split(splitOn).filter { !it.isBlank() }.map { it.trim() })
        }

        @JvmStatic
        fun ofSingleLine(string: String): PuzzleInput {
            return PuzzleInput(listOf(string))
        }
    }
}