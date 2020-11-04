package com.kalvatn.aoc.year2018
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2018D07Test : BaseDayTest() {
  override suspend fun examplePartOne() {
    val test1 = Y2018D07(PuzzleInput.forDay(Year.Y2018, Day.D07, "test"))
    test1.partOne() shouldBe "CABDFE"
  }

  override suspend fun examplePartTwo() {
    val test1 = Y2018D07(PuzzleInput.forDay(Year.Y2018, Day.D07, "test"))
    test1.partTwo().toInt() shouldBe 15
  }

  override suspend fun solutionPartOne() {
    val day = Y2018D07()
    day.partOne() shouldBe "JKNSTHCBGRVDXWAYFOQLMPZIUE"
  }

  override suspend fun solutionPartTwo() {
    val day = Y2018D07()
    day.partTwo().toInt() shouldBe 755
  }
}
