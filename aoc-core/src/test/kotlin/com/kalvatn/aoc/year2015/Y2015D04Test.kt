package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotlintest.shouldBe

class Y2015D04Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    Y2015D04(PuzzleInput.ofSingleLine("abcdef")).partOne().toInt() shouldBe 609043
  }

  @Test
  override suspend fun examplePartTwo() {
    Y2015D04(PuzzleInput.ofSingleLine("abcdef")).partTwo().toInt() shouldBe 6742839
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2015D04().partOne().toInt() shouldBe 254575
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2015D04().partTwo().toInt() shouldBe 1038736
  }
}
