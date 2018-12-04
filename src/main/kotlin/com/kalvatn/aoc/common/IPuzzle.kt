package com.kalvatn.aoc.common

interface IPuzzle {
    val year: Year
    val day: Day
    val input: PuzzleInput

    fun partOne(): String
    fun partTwo(): String

    fun run(runBenchmark: Boolean = false)
}