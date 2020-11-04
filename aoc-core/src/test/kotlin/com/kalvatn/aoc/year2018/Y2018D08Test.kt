package com.kalvatn.aoc.year2018
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2018D08Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D08(PuzzleInput.forDay(Year.Y2018, Day.D08, "test"))
    test1.partOne().toInt() shouldBe 138
  }

  @Test
  override suspend fun examplePartTwo() {
    val test1 = Y2018D08(PuzzleInput.forDay(Year.Y2018, Day.D08, "test"))
    test1.partTwo().toInt() shouldBe 66
  }

  @Test
  override suspend fun solutionPartOne() {
    val day = Y2018D08()
    day.partOne().toInt() shouldBe 48443
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day = Y2018D08()
    day.partTwo().toInt() shouldBe 30063
  }
}
