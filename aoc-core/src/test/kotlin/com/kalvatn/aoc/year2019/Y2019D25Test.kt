package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import io.kotlintest.shouldBe

internal class Y2019D25Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D25().partOne().toInt() shouldBe 20483
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D25().partTwo() shouldBe "https://adventofcode.com/2019/day/25/answer"
  }
}
