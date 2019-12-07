package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2019D07Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
//        val input = PuzzleInput.ofSingleLine("")
        val input = PuzzleInput.forDay(Year.Y2019, Day.D07, "test")
        val day = Y2019D07(input)
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
