package com.kalvatn.aoc.core.input

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Puzzle
import com.kalvatn.aoc.core.model.Year
import java.io.File
import java.nio.file.Paths

@Suppress("unused")
open class PuzzleInput(val lines: List<String>) {

  fun <E> map(fn: (String) -> E): List<E> {
    return lines.map { fn(it) }
  }

  fun singleLine(): String {
    return lines.first()
  }

  fun singleLineSplit(splitOn: String): List<String> {
    return lines.first().split(splitOn).filter { it.isNotBlank() }.map { it.trim() }
  }

  fun singleLineIntegers() = singleLineSplit(",").map { it.toInt() }
  fun singleLineLongs() = singleLineSplit(",").map { it.toLong() }

  fun asIntegers(): List<Int> {
    return map { it.toInt() }
  }

  fun asInteger(): Int {
    return asIntegers().first()
  }

  fun asString(): String {
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
    private const val P1_TEST_SUFFIX = "test_p1"
    private const val P2_TEST_SUFFIX = "test_p2"
    val NULL = PuzzleInput(listOf())

    private fun downloadInput(year: Year, day: Day, inputFile: File) {
      val content = PuzzleInputDownloader.downloadInput(year, day)
      inputFile.parentFile.mkdirs()
      inputFile.createNewFile()
      inputFile.writeText(content)
    }

    @JvmStatic
    fun p1Test(puzzle: Puzzle, case: Int = 1): PuzzleInput {
      return forDay(puzzle, suffix = "${P1_TEST_SUFFIX}_$case")
    }

    @JvmStatic
    fun p1Test(year: Year, day: Day, case: Int = 1): PuzzleInput {
      return forDay(year, day, suffix = "${P1_TEST_SUFFIX}_$case")
    }

    @JvmStatic
    fun p2Test(puzzle: Puzzle, case: Int = 1): PuzzleInput {
      return forDay(puzzle, suffix = "${P2_TEST_SUFFIX}_$case")
    }

    @JvmStatic
    fun p2Test(year: Year, day: Day, case: Int = 1): PuzzleInput {
      return forDay(year, day, suffix = "${P2_TEST_SUFFIX}_$case")
    }

    @JvmStatic
    fun forDay(puzzle: Puzzle, suffix: String = ""): PuzzleInput {
      return forDay(puzzle.year, puzzle.day, suffix)
    }

    @JvmStatic
    fun forDay(year: Year, day: Day, suffix: String = ""): PuzzleInput {
      val root = "/home/kalvatn/work/github/kalvatn/aokotlin"
      val module = "/aoc-${year.intValue()}"
      val inputs = "src/main/resources/inputs"
      val inputDir = Paths.get(root, module, inputs).toFile()
      inputDir.mkdirs()

      val extra = if (suffix.isNotBlank()) "_$suffix" else ""
      val filename = "${year.intString()}/${day.intString()}$extra"
      val inputFile = File(inputDir, filename)
      if (suffix.isBlank() && !inputFile.exists()) {
        println("${inputFile.absolutePath} does not exist")
        downloadInput(year, day, inputFile)
      }
      return PuzzleInput(inputFile.readLines().filter { it.isNotBlank() })
    }

    @JvmStatic
    fun ofCommaDelimited(string: String): PuzzleInput {
      return ofSingleLineSplit(string, splitOn = ",")
    }

    @JvmStatic
    fun ofSingleLineSplit(string: String, splitOn: String = ""): PuzzleInput {
      return PuzzleInput(string.split(splitOn).filter { it.isNotBlank() }.map { it.trim() })
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
