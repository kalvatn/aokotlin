package com.kalvatn.aoc.year2020

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.Turn
import com.kalvatn.aoc.core.model.AbstractTask
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Input
import com.kalvatn.aoc.core.model.NoState
import com.kalvatn.aoc.core.model.Output
import com.kalvatn.aoc.core.model.PartResult
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.year2020.Y2016D01.State
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking



class Y2016D01 : AbstractTask<Int, Int, State>(Year.Y2016, Day.D01) {
  data class State(
    val facing: Direction,
    val current: Point,
    val pointsVisited: List<Point>
  )

  private fun parseInstructions(input: Input) =
    input.singleLineSplit(",").map {
      Turn.fromChar(it[0]) to it.drop(1).toInt()
    }

  private fun state(input: Input): State {
    val instructions = parseInstructions(input)
    return instructions.fold(State(Direction.NORTH, START, listOf(START))) { state, (turn, steps) ->
      val facing = state.facing.turn(turn)
      val points = (1..steps).map { n ->
        state.current + (facing.toPointDiff() * n)
      }
      State(facing, points.last(), state.pointsVisited + points)
    }
  }

  override suspend fun p1(state: State) = coroutineScope {
    PartResult.of {
      START.distance(state.current)
    }
  }

  override suspend fun p2(state: State) = coroutineScope {
    PartResult.of {
      state.pointsVisited.groupingBy { it }
        .eachCount()
        .filter { it.value > 1 }
        .keys.first().distance(START)
    }
  }

  override suspend fun solve(input: Input) = coroutineScope {
    Output.of(year, day) {
      runBlocking {
        val state = state(input)
        val (p1, p2) = awaitAll(
          async { p1(state) },
          async { p2(state) }
        )
        Pair(p1, p2)
      }
    }
  }

  companion object {
    val START = Point(0, 0)
  }

}

