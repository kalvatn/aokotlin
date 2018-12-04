package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import com.kalvatn.aoc.extensions.extractIntegers
import com.kalvatn.aoc.utils.intArray2D


class Day03 : Day {
    constructor() : super(2018, 3)
    constructor(input: PuzzleInput) : super(2018, 3, input)

    data class Claim(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int) {
        companion object {
            fun fromString(line: String): Claim {
                val (id, x, y, width, height) = line.extractIntegers()
                return Claim(id, x, y, width, height)
            }

        }
    }

    private fun createFabric(claims: List<Claim>): Array<Array<Int>> {
        val size = when {
            claims.size < 5 -> 8
            else -> 1000
        }
        val fabric = intArray2D(size)
        for (claim in claims) {
            for (i in claim.x until claim.width + claim.x) {
                for (j in claim.y until claim.height + claim.y) {
                    if (fabric[i][j] == 0) {
                        fabric[i][j] = claim.id
                    } else {
                        fabric[i][j] = -1
                    }

                }
            }
        }
        return fabric
    }

    private val claims = input.map { Claim.fromString(it) }
    private val fabric = createFabric(claims)

    override fun partOne(): String {
        val count = fabric.map { array ->
            array
                .asSequence()
                .filter { it == -1 }
                .count()
        }.sum()
        return count.toString()
    }

    override fun partTwo(): String {
        val overlaps = mutableMapOf<Int, Boolean>()
        for (claim in claims) {
            overlaps[claim.id] = false
            for (i in claim.x until claim.width + claim.x) {
                for (j in claim.y until claim.height + claim.y) {
                    if (fabric[i][j] == -1) {
                        overlaps[claim.id] = true
                    }
                }
            }
        }
        return overlaps.filter { !it.value }.keys.first().toString()
    }
}

