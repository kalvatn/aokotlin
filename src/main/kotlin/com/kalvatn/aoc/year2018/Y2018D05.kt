package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import java.util.*


class Y2018D05(input: PuzzleInput? = null) : APuzzle2018(Day.D05, input) {

    private val polymer: String = this.input.singleLine()
    private val aBBa: Set<Char> = polymer.map { it.toLowerCase() }.toSet()

    private fun Char.reacts(b: Char): Boolean {
        return this != b && this.equals(b, ignoreCase = true)
    }

    private fun react(string: String): Int {
        return string.fold(Stack<Char>()) { acc, c ->
            if (!acc.isEmpty() && acc.last().reacts(c)) {
                acc.pop()
            } else {
                acc.push(c)
            }
            return@fold acc
        }.count()
    }


    override fun partOne(): String {
        return react(polymer).toString()
    }

    override fun partTwo(): String {
        return aBBa.map { it ->
            react(polymer.filter { c -> !c.equals(it, ignoreCase = true) })
        }.min().toString()
    }
}

fun main(args: Array<String>) {
    Y2018D05().run()
}