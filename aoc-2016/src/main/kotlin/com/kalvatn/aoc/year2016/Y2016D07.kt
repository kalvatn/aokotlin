package com.kalvatn.aoc.year2016

import com.kalvatn.aoc.core.model.AbstractTask
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Input
import com.kalvatn.aoc.core.model.NoState
import com.kalvatn.aoc.core.model.Output
import com.kalvatn.aoc.core.model.PartResult
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.extensions.hasXYYX
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Y2016D07 : AbstractTask<Int, Int, NoState>(Year.Y2016, Day.D07) {


  override suspend fun p1(input: Input) = coroutineScope {
    PartResult.of {
      input.lines().filter { line ->
        val hyper = mutableListOf<String>()
        val nonHyper = mutableListOf<String>()
        var inHyper = false
        var seq = ""

        for (c in line) {
          when (c) {
            '[' -> {
              inHyper = true; nonHyper.add(seq); seq = ""
            }
            ']' -> {
              inHyper = false; hyper.add(seq); seq = ""
            }
            else -> seq += c
          }
        }
        if (seq.isNotEmpty()) {
          if (inHyper) {
            hyper.add(seq)
          } else {
            nonHyper.add(seq)
          }
        }
        nonHyper.any { it.hasXYYX() } && hyper.none {
          it.hasXYYX()
        }
      }.count()
    }
  }

  override suspend fun p2(input: Input) = coroutineScope {
    PartResult.of {
      input.lines().filter { line ->
        val hyper = mutableListOf<String>()
        val nonHyper = mutableListOf<String>()
        var inHyper = false
        var seq = ""

        for (c in line) {
          when (c) {
            '[' -> {
              inHyper = true; nonHyper.add(seq); seq = ""
            }
            ']' -> {
              inHyper = false; hyper.add(seq); seq = ""
            }
            else -> seq += c
          }
        }
        if (seq.isNotEmpty()) {
          if (inHyper) {
            hyper.add(seq)
          } else {
            nonHyper.add(seq)
          }
        }
        val abas = nonHyper.flatMap { nh ->
          nh.windowed(3, 1, false)
            .filter { it[0] != it[1] && it[0] == it[2] }
        }
        val babs = hyper.flatMap { h ->
          h.windowed(3, 1, false)
            .filter { it[0] != it[1] && it[0] == it[2] }
        }

        abas.filter { aba ->
          val bab = "${aba[1]}${aba[0]}${aba[1]}"
          babs.contains(bab)
        }.any()
      }.count()
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
  val task = Y2016D07()
//  task.solve(Input.p1Test(Year.Y2016, Day.D07)).print()
  task.solve(Input.forDay(Year.Y2016, Day.D07)).print()
//  task.solve(Input.p2Test(Year.Y2016, Day.D07)).print()
}
