package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.exceptions.Impossiburu
import kotlinx.coroutines.runBlocking

class Y2021D04(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D04, input) {

  companion object {
    const val BOARD_SIZE = 5
  }

  private val lines by lazy { this.input.lines }

  data class BingoBoard(val numbers: List<MutableList<Int>>) {
    private val checked: List<MutableList<Boolean>> = numbers.map { it.map { false }.toMutableList() }
    private val indices = numbers.indices
    private var hasBingo = false

    fun checkNumber(number: Int) {
      (indices).forEach { y ->
        (indices).forEach { x ->
          if (numbers[y][x] == number) {
            checked[y][x] = true
          }
        }
      }
    }

    fun sumAllUnchecked(): Int =
      (indices).flatMap { y ->
        (indices).map { x ->
          if (!checked[y][x]) numbers[y][x] else 0
        }
      }.sum()

    private fun hasFilledHorizontal() =
      checked.map { row -> row.all { it } }.any { it }

    private fun hasFilledVertical() =
      (numbers.indices).map { x ->
        checked.map { it[x] }.all { it }
      }.any { it }

    fun hasBingo(): Boolean {
      hasBingo = hasBingo || hasFilledHorizontal() || hasFilledVertical()
      return hasBingo
    }
  }

  private val numbers = lines.first().split(",").map { it.toInt() }
  private val boards = lines.drop(1).windowed(BOARD_SIZE, BOARD_SIZE).map { board ->
    BingoBoard(board.map { s -> s.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList() })
  }

  override suspend fun partOne(): String {
    numbers.forEach { number ->
      boards.forEach { board ->
        board.checkNumber(number)
        if (board.hasBingo()) {
          return (board.sumAllUnchecked() * number).toString()
        }
      }
    }
    throw Impossiburu()
  }

  override suspend fun partTwo(): String {
    numbers.forEach { number ->
      boards.filterNot { it.hasBingo() }.forEach { board ->
        board.checkNumber(number)
        if (board.hasBingo() && boards.all { it.hasBingo() }) {
          val sumAllUnchecked = board.sumAllUnchecked()
          return (sumAllUnchecked * number).toString()
        }
      }
    }
    throw Impossiburu()
  }
}

fun main() = runBlocking {
  PuzzleRunner.run(Y2021D04())
}
