package com.kalvatn.aoc.year2018
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2018D15Test : BaseDayTest() {
  override suspend fun examplePartOne() {
    //val test1 = Y2018D15(PuzzleInput.ofSingleLine(""))
    val test1 = Y2018D15(PuzzleInput.forDay(Year.Y2018, Day.D15, "test"))
    test1.partOne().toInt() shouldBe 0
  }

  override suspend fun examplePartTwo() {
    //val test1 = Y2018D15(PuzzleInput.ofSingleLine(""))
    val test1 = Y2018D15(PuzzleInput.forDay(Year.Y2018, Day.D15, "test"))
    test1.partOne().toInt() shouldBe 0
  }

  override suspend fun solutionPartOne() {
//    TODO("not implemented")
//    val day = Y2018D15()
//    day.partOne().toInt() shouldBe 0
  }

  override suspend fun solutionPartTwo() {
//    TODO("not implemented")
//    val day = Y2018D15()
//    day.partTwo().toInt() shouldBe 0
  }
}
