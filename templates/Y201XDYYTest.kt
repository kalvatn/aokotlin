package com.kalvatn.aoc.year$YEAR

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y$YEARD$DAYTest : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
//        val input = PuzzleInput.ofSingleLine("")
        val input = PuzzleInput.forDay(Year.Y$YEAR, Day.D$DAY, "test")
        val day = Y$YEARD$DAY(input)
        day.partOne().toInt() shouldBe 0
    }

    override suspend fun examplePartTwo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun solutionPartOne() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun solutionPartTwo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
