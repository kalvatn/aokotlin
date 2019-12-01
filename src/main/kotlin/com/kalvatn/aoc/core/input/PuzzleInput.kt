package com.kalvatn.aoc.core.input

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Puzzle
import com.kalvatn.aoc.core.model.Year
import java.io.File


@Suppress("unused")
open class PuzzleInput(val lines: List<String>) {

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
        val NULL = PuzzleInput(listOf())

        private fun downloadInput(year: Year, day: Day, inputFile: File) {
            val content = PuzzleInputDownloader.downloadInput(year, day)
            inputFile.createNewFile()
            inputFile.writeText(content)

        }

        @JvmStatic
        fun forDay(puzzle: Puzzle, suffix: String = ""): PuzzleInput {
            return forDay(puzzle.year, puzzle.day, suffix)
        }

        @JvmStatic
        fun forDay(year: Year, day: Day, suffix: String = ""): PuzzleInput {

            val extra = if (!suffix.isBlank()) "_$suffix" else ""
            val filename = "src/main/resources/inputs/${year.intString()}/${day.intString()}$extra"

            val inputFile = File(filename)
            if (suffix.isBlank() && !inputFile.exists()) {
                println("${inputFile.absolutePath} does not exist")
                downloadInput(year, day, inputFile)
            }
            return PuzzleInput(inputFile.readLines().filter { !it.isBlank() })
        }

        @JvmStatic
        fun ofCommaDelimited(string: String): PuzzleInput {
            return ofSingleLineSplit(string, splitOn = ",")
        }

        @JvmStatic
        fun ofSingleLineSplit(string: String, splitOn: String = ""): PuzzleInput {
            return PuzzleInput(string.split(splitOn).filter { !it.isBlank() }.map { it.trim() })
        }

        @JvmStatic
        fun ofSingleLine(string: String): PuzzleInput {
            return PuzzleInput(listOf(string))
        }
    }
}