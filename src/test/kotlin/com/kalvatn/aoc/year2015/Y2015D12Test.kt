package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.BaseDayTest
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotlintest.shouldBe

internal class Y2015D12Test : BaseDayTest() {
    @Test
    override suspend fun examplePartOne() {
    }

    @Test
    override suspend fun examplePartTwo() {
        val test1 = PuzzleInput.ofSingleLine("[1,2,3]")
        Y2015D12(test1).partTwo().toInt() shouldBe 6
        val test2 = PuzzleInput.ofSingleLine("[1,{\"c\":\"red\",\"b\":2},3]")
        Y2015D12(test2).partTwo().toInt() shouldBe 4
        val test3 = PuzzleInput.ofSingleLine("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}")
        Y2015D12(test3).partTwo().toInt() shouldBe 0
        val test4 = PuzzleInput.ofSingleLine("[1,\"red\",5]")
        Y2015D12(test4).partTwo().toInt() shouldBe 6
    }

    @Test
    override suspend fun solutionPartOne() {
        Y2015D12().partOne().toInt() shouldBe 191164
    }

    @Test
    override suspend fun solutionPartTwo() {
        Y2015D12().partTwo().toInt() shouldBe 87842
    }
}
