package com.kalvatn.aoc.year$YEAR

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle$YEAR
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y$YEARD$DAY(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle$YEAR(Day.D$DAY, input) {

    val lines = this.input.lines

    override suspend fun partOne(): String {
        return ""
    }

    override suspend fun partTwo(): String {
        return ""
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y$YEARD$DAY())).run()
}

