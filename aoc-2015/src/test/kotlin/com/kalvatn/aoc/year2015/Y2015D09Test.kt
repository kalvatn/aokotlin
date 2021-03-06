package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.JunitTags
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

internal class Y2015D09Test : BaseDayTest() {
  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.forDay(Year.Y2015, Day.D09, "test")
    Y2015D09(input).partOne().toInt() shouldBe 605
  }

  @Test
  override suspend fun examplePartTwo() {
    val input = PuzzleInput.forDay(Year.Y2015, Day.D09, "test")
    Y2015D09(input).partTwo().toInt() shouldBe 982
  }

  @Tags(Tag(JunitTags.SLOW))
  @Test
  override suspend fun solutionPartOne() {
    Y2015D09().partOne().toInt() shouldBe 207
  }

  @Tags(Tag(JunitTags.SLOW))
  @Test
  override suspend fun solutionPartTwo() {
    Y2015D09().partTwo().toInt() shouldBe 804
  }
}
