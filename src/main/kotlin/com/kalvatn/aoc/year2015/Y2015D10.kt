package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

fun String.lookAndSay(): String {
    var current = this.first()
    var count = 0
    val new = mutableListOf<String>()
    this.forEach { c ->
        if (c != current) {
            new.add("$count$current")
            count = 0
            current = c
        }
        count++
    }
    new.add("$count$current")
    return new.joinToString("")
}

class Y2015D10(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D10, input) {

    private val sequenceByIteration by lazy {
        var sequence = this.input.singleLine()
        val iterations = mutableMapOf<Int, String>()
        iterations[0] = sequence
        repeat(50) {
            sequence = sequence.lookAndSay()
            iterations[it + 1] = sequence
        }
        iterations
    }

    override suspend fun partOne(): String {
        return sequenceByIteration[40]!!.length.toString()
    }

    override suspend fun partTwo(): String {
        return sequenceByIteration[50]!!.length.toString()
    }
}

fun main() = runBlocking {
    PuzzleRunner.run(Y2015D10())
}

