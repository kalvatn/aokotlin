package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.Turn
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2019D11(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D11, input) {

  private val program by lazy { this.input.singleLineLongs() }

  enum class Color(val value: Int) {
    BLACK(0),
    WHITE(1);

    companion object {
      private val BY_ID = values().map { it.value to it }.toMap()
      fun fromId(value: Int): Color = BY_ID[value] ?: error("no color with value $value")
    }
  }

  data class PaintRobot(
    private val startPosition: Point = Point(0, 0),
    private val startDirection: Direction = Direction.NORTH,
    private val startColor: Color,
    private val computer: IntcodeComputer
  ) {

    private var currentPosition: Point = startPosition
    private var currentDirection: Direction = startDirection
    private val tiles: MutableMap<Point, Color> = mutableMapOf(
      startPosition to startColor
    )

    private fun paint(color: Color) {
      tiles[currentPosition] = color
    }

    private fun turn(turn: Turn) {
      currentDirection = currentDirection.turn(turn)
    }

    private fun move() {
      currentPosition += currentDirection.toPointDiff()
    }

    fun run(): Map<Point, Color> {
      do {
        val currentColor = tiles.computeIfAbsent(currentPosition) { Color.BLACK }.value.toLong()

        computer.input(currentColor)
        computer.run()

        val (output1, output2) = computer.output().map { it.toInt() }
        computer.clearOutput()

        paint(Color.fromId(output1))
        turn(Turn.fromInt(output2))
        move()
      } while (computer.state() != State.HALT)
      return tiles
    }
  }

  override suspend fun partOne(): String {
    return PaintRobot(
      startColor = Color.BLACK,
      computer = IntcodeComputer(program)
    ).run().size.toString()
  }

  override suspend fun partTwo(): String {
    val tiles = PaintRobot(
      startColor = Color.WHITE,
      computer = IntcodeComputer(program)
    ).run()
    val (xMin, xMax, yMin, yMax) = with(tiles.keys) {
      listOf(
        minByOrNull { it.x }!!.x,
        maxByOrNull { it.x }!!.x,
        minByOrNull { it.y }!!.y,
        maxByOrNull { it.y }!!.y
      )
    }

    (yMin..yMax).forEach { y ->
      (xMin..xMax).forEach { x ->
        print(if (tiles[Point(x, y)] == Color.WHITE) "█" else " ")
      }
      println()
    }
    return "UZAEKBLP"
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D11())).run()
}
