package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

internal class Y2019D08Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D08().partOne().toInt() shouldBe 2500
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D08().partTwo() shouldBe "CYUAH"
  }
}
