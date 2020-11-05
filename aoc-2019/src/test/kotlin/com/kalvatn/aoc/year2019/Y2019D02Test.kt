package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2019D02Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    IntcodeComputer(listOf(1, 0, 0, 0, 99)).run().memory() shouldBe listOf<Long>(2, 0, 0, 0, 99)
    IntcodeComputer(listOf(2, 3, 0, 3, 99)).run().memory() shouldBe listOf<Long>(2, 3, 0, 6, 99)
    IntcodeComputer(listOf(2, 4, 4, 5, 99, 0)).run().memory() shouldBe listOf<Long>(2, 4, 4, 5, 99, 9801)
    IntcodeComputer(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99))
      .run().memory() shouldBe listOf<Long>(30, 1, 1, 4, 2, 5, 6, 0, 99)
  }

  @Test
  override suspend fun examplePartTwo() {
    val computer = IntcodeComputer(PuzzleInput.forDay(Year.Y2019, Day.D02).singleLineLongs())
    computer.findVerbNounPairThatProducesSolution(3654868) shouldBe Pair(12, 2)
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D02().partOne().toInt() shouldBe 3654868
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D02().partTwo().toInt() shouldBe 7014
  }
}
