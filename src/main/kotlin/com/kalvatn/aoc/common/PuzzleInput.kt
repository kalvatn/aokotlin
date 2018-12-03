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
        fun forDay(day: IDay, suffix: String = ""): PuzzleInput {
            return forDay(day.year, day.day, suffix)
        }

        @JvmStatic
        fun forDay(year: Int, day: Int, suffix: String = ""): PuzzleInput {

            val dayZeroPad = day.toString().padStart(2, '0')
            val extra = if (!suffix.isBlank()) "_$suffix" else ""
            val filename = "src/main/resources/inputs/$year/$dayZeroPad$extra"

            return PuzzleInput(File(filename).readLines().filter { !it.isBlank() })
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