package com.kalvatn.aoc.year.$YEAR

import org.junit.jupiter.api.Test

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import kotlinx.coroutines.runBlocking
import io.kotest.matchers.shouldBe

internal class Y$YEARD$DAYTest {

  private fun puzzle()


  @Test
  fun examplePartOne() = runBlocking {
//        val input = PuzzleInput.ofSingleLine("")
    val input = PuzzleInput.forDay(Year.Y$YEAR, Day.D$DAY, "test")
    Y$YEARD$DAY(input).partOne().toInt() shouldBe 0
  }

  @Test
  fun examplePartTwo() = runBlocking {
//        val input = PuzzleInput.ofSingleLine("")
    val input = PuzzleInput.forDay(Year.Y$YEAR, Day.D$DAY, "test")
    Y$YEARD$DAY(input).partTwo().toInt() shouldBe 0
  }

  @Test
  fun solutionPartOne() = runBlocking {
    Y$YEARD$DAY().partOne().toInt() shouldBe 0
  }

  @Test
  fun solutionPartTwo() = runBlocking {
    Y$YEARD$DAY().partTwo().toInt() shouldBe 0
  }
}
