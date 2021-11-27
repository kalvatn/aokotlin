package com.kalvatn.aoc.year2020

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.extensions.reductions
import com.kalvatn.aoc.utils.timeit
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

interface Puzzle<T1, T2> {
  suspend fun solve(input: Input): Output<T1, T2>
}

class Y2020D01 : Puzzle<Int, Int> {

  private suspend fun p1(changes: List<Int>) = coroutineScope {
    val (i, duration) = timeit { changes.sum() }
    PartResult(i, duration)
  }

  private suspend fun p2(changes: List<Int>) = coroutineScope {
    val (i, duration) = timeit { changes.reductions(0) { acc, i -> acc + i }.takeWhile { it != -1 }.count() }
    PartResult(i, duration)
  }

  @Override
  override suspend fun solve(input: Input): Output<Int, Int> = coroutineScope {
    val changes: List<Int> = input.singleLine().map {
      when (it) {
        '(' -> 1
        else -> -1
      }
    }
    val p1 = async { p1(changes) }
    val p2 = async { p2(changes) }

    Output(Year.Y2020, Day.D01, p1.await(), p2.await())
  }
}

fun main() = runBlocking {
  val input = Input.p1Test(Year.Y2020, Day.D01, 1)
  Y2020D01().solve(input).print()
}
