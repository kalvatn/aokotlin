package com.kalvatn.aoc.core.input

import com.kalvatn.aoc.common.model.Point
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

    fun singleLineSplit(splitOn: String): List<String> {
        return lines.first().split(splitOn).filter { !it.isBlank() }.map { it.trim() }
    }

    fun singleLineIntegers() = singleLineSplit(",").map { it.toInt() }
    fun singleLineLongs() = singleLineSplit(",").map { it.toLong() }

    fun asIntegers(): List<Int> {
        return map { it.toInt() }
    }

    fun asInteger(): Int {
        return asIntegers().first()
    }

    fun asString():String {
        return lines.joinToString("\n")
    }


    fun asPoints(): Map<Point, Char> {
        val points = mutableMapOf<Point, Char>()
        lines.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                points[Point(x, y)] = c
            }
        }
        return points
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
            val filename = "/inputs/${year.intString()}/${day.intString()}$extra"

          val inputFile = File(PuzzleInput::class.java.getResource(filename).file)
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

        @JvmStatic
        fun ofString(s: String): PuzzleInput {
            return PuzzleInput(s.split("\n"))
        }
    }
}
