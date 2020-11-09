package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.core.model.AbstractTask
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Input
import com.kalvatn.aoc.core.model.NoState
import com.kalvatn.aoc.core.model.Output
import com.kalvatn.aoc.core.model.PartResult
import com.kalvatn.aoc.core.model.Year
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Y2016D06 : AbstractTask<String, String, NoState>(Year.Y2016, Day.D06) {

  fun columnFrequencies(input: Input, sort: Comparator<Pair<Char, Int>>): List<Pair<Char, Int>> {
    val lines = input.lines()
    val columns = mutableListOf<MutableList<Char>>()
    val numColumns = lines.first().length
    repeat(numColumns) {
      columns += mutableListOf<Char>()
    }
    lines.forEach { line ->
      line.indices.forEach {
        columns[it].add(line[it])
      }
    }
    return columns.map { column ->
      column.groupingBy { it }
        .eachCount()
        .toList()
        .sortedWith(sort)
        .first()
    }
  }

  override suspend fun p1(input: Input) = coroutineScope {
    PartResult.of {
      columnFrequencies(
        input = input,
        sort = compareBy { -it.second }
      ).map { it.first }.joinToString("")
    }
  }

  override suspend fun p2(input: Input) = coroutineScope {
    PartResult.of {
      columnFrequencies(
        input = input,
        sort = compareBy { it.second }
      ).map { it.first }.joinToString("")
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
  val task = Y2016D06()
//  task.solve(Input.p1Test(Year.Y2016, Day.D06)).print()
  task.solve(Input.forDay(Year.Y2016, Day.D06)).print()
}
