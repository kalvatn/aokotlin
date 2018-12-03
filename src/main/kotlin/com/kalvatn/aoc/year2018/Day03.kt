package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput

data class Claim(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int) {
    companion object {
        fun fromString(line: String): Claim {
            val (id, rect) = line.slice(1 until line.length).split('@').map { it.trim() }
            val (pos, size) = rect.split(":").map { it.trim() }
            val (x, y) = pos.split(",").map { it.trim().toInt() }
            val (width, height) = size.split("x").map { it.trim().toInt() }
            return Claim(id.toInt(), x, y, width, height)
        }
    }
}

class Day03 : Day(2018, 3) {

    @Suppress("RedundantOverride")
    override fun input(): PuzzleInput {
//        return PuzzleInput.forDay(this, "test1")
        return super.input()
    }

    private fun createFabric(claims: List<Claim>): Array<Array<Int>> {
        val size = when {
            claims.size < 5 -> 8
            else -> 1000
        }
        var fabric = arrayOf<Array<Int>>()
        for (i in 0 until size) {
            var array = arrayOf<Int>()
            for (j in 0 until size) {
                array += 0
            }
            fabric += array
        }
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

    private val claims = input().lines.map { Claim.fromString(it) }
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

