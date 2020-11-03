package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D24Test : BaseDayTest() {

  @Test
  fun testGameOfLife() {
    val input1 = PuzzleInput.forDay(Year.Y2019, Day.D24, "test")
    val initialState = input1.asPoints()
    val game = Y2019D24.GameOfLife(initialState)
    game.toString() shouldBe input1.asString()

    game.step()
    game.minute shouldBe 1
    game.toString() shouldBe """
           |#..#.
           |####.
           |###.#
           |##.##
           |.##..""".trimMargin()

    game.step()
    game.minute shouldBe 2
    game.toString() shouldBe """
|#####
|....#
|....#
|...#.
|#.###
""".trimMargin()
    game.step()
    game.minute shouldBe 3
    game.toString() shouldBe """
|#....
|####.
|...##
|#.##.
|.##.#
""".trimMargin()
    game.step()
    game.minute shouldBe 4
    game.toString() shouldBe """
|####.
|....#
|##..#
|.....
|##...
""".trimMargin()
  }

  @Test
  fun testSimulateUntilRepeating() {
    val input1 = PuzzleInput.forDay(Year.Y2019, Day.D24, "test")
    val initialState = input1.asPoints()
    val game = Y2019D24.GameOfLife(initialState)

    game.simulate()
    game.toString() shouldBe """
|.....
|.....
|.....
|#....
|.#...
""".trimMargin()
  }

  @Test
  fun testBiodiversityRating() {
    val input1 = PuzzleInput.forDay(Year.Y2019, Day.D24, "test")
    val initialState = input1.asPoints()
    val game = Y2019D24.GameOfLife(initialState)

    game.simulate()
    game.biodiversity() shouldBe 2129920
  }

  @Test
  override suspend fun examplePartOne() {
    val input = PuzzleInput.forDay(Year.Y2019, Day.D24, "test")
    Y2019D24(input).partOne().toInt() shouldBe 2129920
  }

  @Test
  override suspend fun examplePartTwo() {
    val input1 = PuzzleInput.forDay(Year.Y2019, Day.D24, "test")
    val initialState = input1.asPoints()
    val game = Y2019D24.GameOfLifeRecursive(initialState)

    game.simulate(10)
    game.countBugs() shouldBe 99
  }

  @Test
  override suspend fun solutionPartOne() {
    Y2019D24().partOne().toInt() shouldBe 18852849
  }

  @Test
  override suspend fun solutionPartTwo() {
    Y2019D24().partTwo().toInt() shouldBe 1948
  }
}
