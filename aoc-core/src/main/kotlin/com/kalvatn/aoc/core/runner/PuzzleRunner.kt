package com.kalvatn.aoc.core.runner

import com.kalvatn.aoc.core.model.Puzzle
import com.kalvatn.aoc.utils.timeit
import com.kalvatn.aoc.utils.toHMS
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PuzzleRunner(private val puzzles: List<Puzzle> = listOf()) {
  suspend fun run() = coroutineScope {
    timeit {
      puzzles.map { async { it.run() } }.awaitAll()
    }.let {
      println()
      println()
      println("${puzzles.size} puzzles finished in ${it.second.toHMS()}")
    }
  }

  companion object {
    suspend fun run(vararg puzzle: Puzzle) {
      PuzzleRunner(puzzle.toList()).run()
    }
  }
}
