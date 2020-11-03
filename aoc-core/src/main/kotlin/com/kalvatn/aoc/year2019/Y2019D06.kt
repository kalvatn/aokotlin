package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.graph.Graph
import com.kalvatn.aoc.common.graph.distances
import com.kalvatn.aoc.common.graph.shortestPath
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2019D06(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D06, input) {

  private val graph by lazy {
    Graph<String>().also { graph ->
      this.input.lines.map {
        val (first, second) = it.split(')')
        graph.connect(first, second)
      }
    }
  }

  override suspend fun partOne(): String {
    return graph.distances(COM).values.sum().toString()
  }

  override suspend fun partTwo(): String {
    val start = graph.parentOf(YOU)
    val target = graph.parentOf(SAN)
    return (graph.shortestPath(start, target)).toString()
  }

  companion object {
    const val COM = "COM"
    const val YOU = "YOU"
    const val SAN = "SAN"
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D06())).run()
}
