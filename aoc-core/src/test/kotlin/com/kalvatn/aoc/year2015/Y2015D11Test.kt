package com.kalvatn.aoc.year2015
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import io.kotest.matchers.shouldBe

internal class Y2015D11Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D11().partOne() shouldBe "hepxxyzz"
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2015D11().partTwo() shouldBe "heqaabcc"
  }
}
