package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotest.matchers.shouldBe

class Y2015D01Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    Y2015D01(PuzzleInput.ofSingleLine(")")).partOne().toInt() shouldBe -1
  }

  @Test
  override suspend fun examplePartTwo() {
    Y2015D01(PuzzleInput.ofSingleLine(")")).partTwo().toInt() shouldBe 1
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D01().partOne().toInt() shouldBe 138
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2015D01().partTwo().toInt() shouldBe 1771
  }
}
