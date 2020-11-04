package com.kalvatn.aoc.year2015
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2015D07Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.forDay(Year.Y2015, Day.D07, "test")
    Y2015D07(input).partOne().toInt() shouldBe 65412
  }

  @Test
  override suspend fun examplePartTwo() {
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D07().partOne().toInt() shouldBe 16076
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2015D07().partTwo().toInt() shouldBe 2797
  }
}
