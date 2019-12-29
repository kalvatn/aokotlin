package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2015
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.extensions.doesNotContain
import com.kalvatn.aoc.extensions.hasConsecutiveLetters
import com.kalvatn.aoc.extensions.hasIncreasingLetterStraight
import kotlinx.coroutines.runBlocking

fun Char.increment() = if (this.inc() > 'z') 'a' else this.inc()

fun String.increment(): String {
    tailrec fun inc(index: Int, incremented: List<Char> = listOf()): String {
        if (index < 0) {
            return incremented.reversed().joinToString("")
        }
        val c = this[index].increment()
        if (c == 'a') {
            return inc(index.dec(), incremented + c)
        }
        return (take(index).toList() + c + incremented.reversed()).joinToString("")
    }
    return inc(lastIndex)
}

class Y2015D11(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2015(Day.D11, input) {

    private val password by lazy { this.input.singleLine() }

    private val p1 by lazy { findNextPassword(password) }

    private fun findNextPassword(password: String): String {
        var new = password
        while (true) {
            new = new.increment()
            if (
                    new.doesNotContain(*BAD_CHARS) &&
                    new.hasIncreasingLetterStraight(3) &&
                    new.hasConsecutiveLetters(2)) {
                return new
            }
        }
    }

    override suspend fun partOne(): String {
        return p1
    }

    override suspend fun partTwo(): String {
        return findNextPassword(p1)
    }

    companion object {
        val BAD_CHARS = listOf('i', 'o', 'l').toCharArray()
    }
}

fun main() = runBlocking {
    PuzzleRunner.run(Y2015D11())
}

