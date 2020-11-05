package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.core.model.AbstractTask
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Input
import com.kalvatn.aoc.core.model.NoState
import com.kalvatn.aoc.core.model.Output
import com.kalvatn.aoc.core.model.PartResult
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Y2016D03 : AbstractTask<Int, Int, NoState>(Year.Y2016, Day.D03) {

  private fun validTriangle(s1: Int, s2: Int, s3: Int): Boolean {
    return (s1 + s2 > s3) && (s2 + s3 > s1) && (s1 + s3 > s2)
  }

  override suspend fun p1(input: Input) = coroutineScope {
    PartResult.of {
      input.lines()
        .map { line -> line.extractIntegers() }
        .filter { validTriangle(it[0], it[1], it[2]) }
        .count()
    }
  }

  override suspend fun p2(input: Input) = coroutineScope {
    PartResult.of {
      val numbers = input.lines()
        .map { line -> line.extractIntegers() }
      val triangles = mutableListOf<List<Int>>()
      for (row in numbers.indices step 3) {
        triangles += (0..2).map { col ->
          listOf(numbers[row][col], numbers[row + 1][col], numbers[row + 2][col])
        }
      }
      triangles.filter { validTriangle(it[0], it[1], it[2]) }.count()
    }
  }

  override suspend fun solve(input: Input) = coroutineScope {
    Output.of(year, day) {
      runBlocking {
        val (p1, p2) = awaitAll(
          async { p1(input) },
          async { p2(input) }
        )
        Pair(p1, p2)
      }
    }
  }

}

fun main() = runBlocking {
  val task = Y2016D03()
  task.solve(Input.forDay(Year.Y2016, Day.D03)).print()
}
