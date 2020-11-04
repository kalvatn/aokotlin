package com.kalvatn.aoc.year2018
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2018D02Test : BaseDayTest() {

  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D02(PuzzleInput.forDay(Year.Y2018, Day.D02, "test1"))
    val test2 = Y2018D02(PuzzleInput.forDay(Year.Y2018, Day.D02, "test2"))
    test1.partOne().toInt() shouldBe 12
    test2.partOne().toInt() shouldBe 0
  }

  @Test
  override suspend fun examplePartTwo() {
    val test1 = Y2018D02(PuzzleInput.forDay(Year.Y2018, Day.D02, "test1"))
    val test2 = Y2018D02(PuzzleInput.forDay(Year.Y2018, Day.D02, "test2"))
    test1.partTwo() shouldBe "abcde"
    test2.partTwo() shouldBe "fgij"
  }

  @Test
  override suspend fun solutionPartOne() {
    val day02 = Y2018D02()
    day02.partOne().toInt() shouldBe 5727
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day02 = Y2018D02()
    day02.partTwo() shouldBe "uwfmdjxyxlbgnrotcfpvswaqh"
  }
}
