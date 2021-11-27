package com.kalvatn.aoc.year2020

import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInputDownloader
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Puzzle
import com.kalvatn.aoc.core.model.Year
import java.io.File

@Suppress("unused")
interface Input {
  fun lines(): List<String>
  fun <E> map(fn: (String) -> E): List<E> {
    return lines().map { fn(it) }
  }

  fun singleLine(): String {
    return lines().first()
  }

  fun singleLineSplit(splitOn: String): List<String> {
    return lines().first().split(splitOn).filter { it.isNotBlank() }.map { it.trim() }
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
    return lines().joinToString("\n")
  }

  fun asPoints(): Map<Point, Char> {
    val points = mutableMapOf<Point, Char>()
    lines().forEachIndexed { y, s ->
      s.forEachIndexed { x, c ->
        points[Point(x, y)] = c
      }
    }
    return points
  }

  companion object {
    private const val P1_TEST_SUFFIX = "test_p1"
    private const val P2_TEST_SUFFIX = "test_p2"

    @JvmStatic
    fun p1Test(puzzle: Puzzle, case: Int = 1): FileInput {
      return forDay(puzzle, suffix = "${P1_TEST_SUFFIX}_$case")
    }

    @JvmStatic
    fun p1Test(year: Year, day: Day, case: Int = 1): FileInput {
      return forDay(year, day, suffix = "${P1_TEST_SUFFIX}_$case")
    }

    @JvmStatic
    fun p2Test(puzzle: Puzzle, case: Int = 1): FileInput {
      return forDay(puzzle, suffix = "${P2_TEST_SUFFIX}_$case")
    }

    @JvmStatic
    fun p2Test(year: Year, day: Day, case: Int = 1): FileInput {
      return forDay(year, day, suffix = "${P2_TEST_SUFFIX}_$case")
    }

    @JvmStatic
    fun forDay(puzzle: Puzzle, suffix: String = ""): FileInput {
      return forDay(puzzle.year, puzzle.day, suffix)
    }

    @JvmStatic
    fun forDay(year: Year, day: Day, suffix: String = ""): FileInput {
      val extra = if (suffix.isNotBlank()) "_$suffix" else ""
      val filename = "/inputs/${year.intString()}/${day.intString()}$extra"

      val inputFile = File(FileInput::class.java.getResource(filename)!!.file)
      if (suffix.isBlank() && !inputFile.exists()) {
        println("${inputFile.absolutePath} does not exist")
        val content = PuzzleInputDownloader.downloadInput(year, day)
        inputFile.createNewFile()
        inputFile.writeText(content)
      }
      return FileInput(inputFile)
    }

    @JvmStatic
    fun ofCommaDelimited(string: String): Input {
      return ofSingleLineSplit(string, splitOn = ",")
    }

    @JvmStatic
    fun ofSingleLineSplit(string: String, splitOn: String = ""): Input {
      return StringInput(string, splitOn)
    }

    @JvmStatic
    fun ofSingleLine(string: String): Input {
      return StringInput(string)
    }

    @JvmStatic
    fun ofString(s: String): Input {
      return StringInput(s, "\n")
    }
  }
}
