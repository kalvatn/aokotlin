package com.kalvatn.aoc.year2019
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

internal class Y2019D21Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D21().partOne().toInt() shouldBe 19359316
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D21().partTwo().toInt() shouldBe 1141281622
  }
}
