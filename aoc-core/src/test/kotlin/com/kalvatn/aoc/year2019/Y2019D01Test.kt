package com.kalvatn.aoc.year2019
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

internal class Y2019D01Test : BaseDayTest() {

  @Test
  fun testCalcFuel() {
    val day = Y2019D01(PuzzleInput.ofSingleLine("100756"))
    day.fuelPartOne(12) shouldBe 2
    day.fuelPartOne(14) shouldBe 2
    day.fuelPartOne(1969) shouldBe 654
    day.fuelPartOne(100756) shouldBe 33583
  }

  @Test
  fun testCalcFuelPartTwo() {
    val day = Y2019D01(PuzzleInput.ofSingleLine("100756"))
    day.fuelPartTwo(12) shouldBe 2
    day.fuelPartTwo(14) shouldBe 2
    day.fuelPartTwo(1969) shouldBe 966
    day.fuelPartTwo(100756) shouldBe 50346
  }

  @Test
  override suspend fun examplePartOne() {
    val day = Y2019D01(PuzzleInput.ofSingleLine("100756"))
    day.partOne().toInt() shouldBe 33583
  }

  @Test
  override suspend fun examplePartTwo() {
    val day = Y2019D01(PuzzleInput.forDay(Year.Y2019, Day.D01, "test1"))
    day.partTwo().toInt() shouldBe 50346
  }

  @Test
  override suspend fun solutionPartOne() {
    val day = Y2019D01()
    day.partOne().toInt() shouldBe 3273471
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day = Y2019D01()
    day.partTwo().toInt() shouldBe 4907345
  }
}
