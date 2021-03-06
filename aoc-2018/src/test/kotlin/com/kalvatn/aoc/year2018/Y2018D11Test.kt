package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.JunitTags
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

class Y2018D11Test : BaseDayTest() {

  @Test
  fun testFuelCell() {
    Y2018D11.FuelCell(Point(3, 5), 8).powerLevel() shouldBe 4
    Y2018D11.FuelCell(Point(122, 79), 57).powerLevel() shouldBe -5
    Y2018D11.FuelCell(Point(217, 196), 39).powerLevel() shouldBe 0
    Y2018D11.FuelCell(Point(101, 153), 71).powerLevel() shouldBe 4
  }

  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D11(PuzzleInput.ofSingleLine("18"))
    test1.partOne() shouldBe "33,45"
    val test2 = Y2018D11(PuzzleInput.ofSingleLine("42"))
    test2.partOne() shouldBe "21,61"
  }

  @Test
  override suspend fun examplePartTwo() {
//    val test1 = Y2018D11(PuzzleInput.ofSingleLine(""))
//    test1.partTwo().toInt() shouldBe 0
  }

  @Test
  override suspend fun solutionPartOne() {
    val day = Y2018D11()
    day.partOne() shouldBe "33,54"
  }

  @Tags(Tag(JunitTags.VERY_SLOW))
  @Test
  override suspend fun solutionPartTwo() {
    val day = Y2018D11()
    day.partTwo() shouldBe "232,289,8"
  }
}
