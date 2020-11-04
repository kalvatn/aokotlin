package com.kalvatn.aoc.year2018
import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe

class Y2018D04Test : BaseDayTest() {

  @Test
  fun eventFromString() {
    val p1 = Y2018D04.GuardRecord.fromString("[1518-11-01 00:00] Guard #10 begins shift")
    p1 shouldBe Y2018D04.GuardRecord(1518, 11, 1, 0, 0, 10, Y2018D04.Action.BEGINS)
    val p2 = Y2018D04.GuardRecord.fromString("[1518-11-01 00:05] falls asleep")
    p2 shouldBe Y2018D04.GuardRecord(1518, 11, 1, 0, 5, -1, Y2018D04.Action.SLEEPS)
    val p3 = Y2018D04.GuardRecord.fromString("[1518-11-01 00:25] wakes up")
    p3 shouldBe Y2018D04.GuardRecord(1518, 11, 1, 0, 25, -1, Y2018D04.Action.WAKES)
  }

  @Test
  override suspend fun examplePartOne() {
    val test1 = Y2018D04(PuzzleInput.forDay(Year.Y2018, Day.D04, "test1"))
    test1.partOne().toInt() shouldBe 240
  }

  @Test
  override suspend fun examplePartTwo() {
    val test1 = Y2018D04(PuzzleInput.forDay(Year.Y2018, Day.D04, "test1"))
    test1.partTwo().toInt() shouldBe 4455
  }

  @Test
  override suspend fun solutionPartOne() {
    val day = Y2018D04()
    day.partOne().toInt() shouldBe 30630
  }

  @Test
  override suspend fun solutionPartTwo() {
    val day = Y2018D04()
    day.partTwo().toInt() shouldBe 136571
  }
}
