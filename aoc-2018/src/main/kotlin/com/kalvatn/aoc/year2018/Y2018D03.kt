package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzleYearDay
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.extensions.extractIntegers
import com.kalvatn.aoc.utils.intArray2D
import kotlinx.coroutines.runBlocking

class Y2018D03(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzleYearDay(Year.Y2018, Day.D03, input) {

  data class Claim(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int) {
    companion object {
      fun fromString(line: String): Claim {
        val (id, x, y, width, height) = line.extractIntegers()
        return Claim(id, x, y, width, height)
      }
    }
  }

  private fun createFabric(claims: List<Claim>): Array<IntArray> {
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

  private val claims = this.input.map { Claim.fromString(it) }
  private val fabric = createFabric(claims)

  override suspend fun partOne(): String {
    val count = fabric.sumOf { array ->
      array
        .asSequence()
        .filter { it == -1 }
        .count()
    }
    return count.toString()
  }

  override suspend fun partTwo(): String {
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

fun main() = runBlocking {
  Y2018D03().run()
}
