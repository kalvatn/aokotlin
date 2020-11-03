package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import io.kotlintest.shouldBe

internal class Y2019D11Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D11().partOne().toInt() shouldBe 2336
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D11().partTwo() shouldBe "UZAEKBLP"
  }
}
