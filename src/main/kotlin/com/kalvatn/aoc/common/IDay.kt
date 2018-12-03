package com.kalvatn.aoc.common

interface IDay {
    val year: Int
    val day: Int
    val input: PuzzleInput

    fun partOne(): String
    fun partTwo(): String

    fun run(runBenchmark: Boolean = false)
}