package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.core.model.AbstractTask
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Input
import com.kalvatn.aoc.core.model.NoState
import com.kalvatn.aoc.core.model.Output
import com.kalvatn.aoc.core.model.PartResult
import com.kalvatn.aoc.core.model.Year
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Y2016D04 : AbstractTask<Int, Int, NoState>(Year.Y2016, Day.D04) {

  override suspend fun p1(input: Input) = coroutineScope {
    PartResult.of {
      val regex: Regex = "^([\\w-]+)-([\\d]+)\\[([\\w]+)]$".toRegex()

      val valid = mutableListOf<Int>()
      input.lines().forEach { line ->
        val (name, sector, checksum) = regex.matchEntire(line)?.destructured ?: error("")
        val charCounts = name
          .replace("-", "")
          .groupingBy { it }
          .eachCount()
          .toList()
          .sortedWith(compareBy({ -it.second }, { it.first }))
        if (checksum.mapIndexed { i, c -> charCounts[i].first == c }.all { it }) {
          valid.add(sector.toInt())
        }
      }
      valid.sum()
    }
  }

  override suspend fun p2(input: Input) = coroutineScope {
    PartResult.of {
      val regex: Regex = "^([\\w-]+)-([\\d]+)\\[([\\w]+)]$".toRegex()

      val valid = mutableMapOf<String, Int>()
      input.lines().forEach { line ->
        val (name, sector, checksum) = regex.matchEntire(line)?.destructured ?: error("")
        val charCounts = name
          .replace("-", "")
          .groupingBy { it }
          .eachCount()
          .toList()
          .sortedWith(compareBy({ -it.second }, { it.first }))
        if (checksum.mapIndexed { i, c -> charCounts[i].first == c }.all { it }) {
          valid[name] = sector.toInt()
        }
      }

      valid.map { (name, sectorId) ->
        name.map {
          when {
            it.isLowerCase() -> {
              val x = it + sectorId % 26; if (x > 'z') (x - 26) else x
            }
            it == '-' -> ' '
            else -> it
          }
        }.joinToString("") to sectorId
      }.first { (name, _) ->
        name.contains("object")
      }.second
    }
  }

  override suspend fun solve(input: Input) = coroutineScope {
    Output.of(year, day) {
      runBlocking {
        val (p1, p2) = awaitAll(
          async { p1(input) },
          async { p2(input) }
        )
        Pair(p1, p2)
      }
    }

  }
}

fun main() = runBlocking {
  val task = Y2016D04()
//  task.solve(Input.ofString("aaaaa-bbb-z-y-x-123[abxyz]")).print()
//  task.solve(Input.ofString("qzmt-zixmtkozy-ivhz-343[zimthkoqvxy]")).print()
  task.solve(Input.forDay(Year.Y2016, Day.D04)).print()
}
