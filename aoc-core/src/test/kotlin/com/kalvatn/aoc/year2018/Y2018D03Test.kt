package com.kalvatn.aoc.year2018
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2018D03Test : BaseDayTest() {

  @Test
  fun testClaim() {
    val claim1 = Y2018D03.Claim.fromString("#1 @ 1,3: 4x4")
    claim1.id shouldBe 1
    claim1.x shouldBe 1
    claim1.y shouldBe 3
    claim1.width shouldBe 4
    claim1.height shouldBe 4
  }

  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D03(PuzzleInput.forDay(Year.Y2018, Day.D03, "test1"))
    test1.partOne().toInt() shouldBe 4
  }

  @Test
  override suspend fun examplePartTwo() {
    val test1 = Y2018D03(PuzzleInput.forDay(Year.Y2018, Day.D03, "test1"))
    test1.partTwo().toInt() shouldBe 3
  }

  @Test
  override suspend fun solutionPartOne() {
    val day03 = Y2018D03()
    day03.partOne().toInt() shouldBe 116140
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day03 = Y2018D03()
    day03.partTwo().toInt() shouldBe 574
  }
}
