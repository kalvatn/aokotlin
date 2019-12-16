package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2016
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking


class Y2016D03(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2016(Day.D03, input) {

    private val triangles by lazy {
        this.input.map { line ->
            line.split(" ").filter { !it.isBlank() }.map { it.toInt() }
        }
    }

    private fun isValidTriangle(x: Int, y: Int, z: Int) =
            x + y > z && x + z > y && y + z > x


    override suspend fun partOne(): String {
        return triangles.filter {
            isValidTriangle(it[0], it[1], it[2])
        }.count().toString()
    }

    override suspend fun partTwo(): String {
        val c1 = triangles.map { it[0] }.chunked(3).filter { isValidTriangle(it[0], it[1], it[2]) }.count()
        val c2 = triangles.map { it[1] }.chunked(3).filter { isValidTriangle(it[0], it[1], it[2]) }.count()
        val c3 = triangles.map { it[2] }.chunked(3).filter { isValidTriangle(it[0], it[1], it[2]) }.count()
        return (c1 + c2 + c3).toString()
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2016D03())).run()
}

