package com.kalvatn.aoc.core

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.year2015.Y2015D04
import com.kalvatn.aoc.year2015.Y2015D06
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class PuzzleInputTest : StringSpec({
    "single line" {
        val i = PuzzleInput.ofSingleLine("2x3x4")
        i.singleLine() shouldBe "2x3x4"

        val j = PuzzleInput.ofSingleLineSplit("2x3x4", "x")
        j.lines shouldBe listOf("2", "3", "4")
    }

    "single line split" {
        val i = PuzzleInput.ofSingleLineSplit("2x3x4", "x")
        i.lines shouldBe listOf("2", "3", "4")
        val j = PuzzleInput.ofSingleLineSplit("2x3x4")
        j.lines shouldBe listOf("2", "x", "3", "x", "4")
    }

    "comma delimited" {
        val i = PuzzleInput.ofCommaDelimited("2,3,4")
        i.lines shouldBe listOf("2", "3", "4")
    }

    "as integers" {
        val i = PuzzleInput.ofCommaDelimited("2,3,4")
        i.asIntegers() shouldBe listOf(2, 3, 4)
    }

    "as single integer" {
        val i = PuzzleInput.ofSingleLine("234")
        i.asInteger() shouldBe 234
    }

    "for day" {
        val puzzle = Y2015D04()
        val i = PuzzleInput.forDay(puzzle)
        i.singleLine() shouldBe "bgvyzdsv"
    }

    "for day with suffix" {
        val puzzle = Y2015D06()
        val i = PuzzleInput.forDay(puzzle, suffix = "test")
        i.lines shouldBe listOf("turn on 0,0 through 0,0", "toggle 0,0 through 999,999")
    }
})