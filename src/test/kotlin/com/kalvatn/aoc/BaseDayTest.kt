package com.kalvatn.aoc

import org.junit.Test


abstract class BaseDayTest {

    @Test
    abstract suspend fun examplePartOne()

    @Test
    abstract suspend fun examplePartTwo()

    @Test
    abstract suspend fun solutionPartOne()

    @Test
    abstract suspend fun solutionPartTwo()
}
