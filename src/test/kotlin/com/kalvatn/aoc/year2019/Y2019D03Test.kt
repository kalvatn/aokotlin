package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D03Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
//        val input = PuzzleInput.ofSingleLine("")
        val input1 = PuzzleInput.forDay(Year.Y2019, Day.D03, "test")
        Y2019D03(input1).partOne().toInt() shouldBe 159

        val input2 = PuzzleInput.forDay(Year.Y2019, Day.D03, "test2")
        Y2019D03(input2).partOne().toInt() shouldBe 135
    }

    @Test
    override suspend fun examplePartTwo() {
        val input1 = PuzzleInput.forDay(Year.Y2019, Day.D03, "test")
        Y2019D03(input1).partTwo().toInt() shouldBe 610

        val input2 = PuzzleInput.forDay(Year.Y2019, Day.D03, "test2")
        Y2019D03(input2).partTwo().toInt() shouldBe 410
    }

    override suspend fun solutionPartOne() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun solutionPartTwo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
