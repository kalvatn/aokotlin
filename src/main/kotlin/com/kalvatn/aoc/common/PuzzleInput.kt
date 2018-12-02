package com.kalvatn.aoc.common

import java.io.File

@Suppress("unused")
class PuzzleInput(val lines: List<String>) {

    fun asIntegerList(): List<Int> {
        return lines.map { it.toInt() }
    }

    fun asSingleInteger(): Int {
        return lines[0].toInt()
    }

    companion object {
        fun forDay(day: IDay, suffix: String = ""): PuzzleInput {

            val year = day.year
            val dayZeroPad = day.day.toString().padStart(2, '0')
            val extra = if (!suffix.isBlank()) "_$suffix" else ""
            val filename = "src/main/resources/inputs/$year/$dayZeroPad$extra"

            return PuzzleInput(File(filename).readLines().filter { !it.isBlank() })
        }

        fun ofCommaDelimited(string: String): PuzzleInput {
            return ofString(string, delimiter = ",")
        }

        fun ofString(string: String, delimiter: String = ""): PuzzleInput {
            return PuzzleInput(string.split(delimiter).map { it.trim() })
        }
    }
}