package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2021D04(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D04, input) {

  private val lines by lazy { this.input.lines }

  data class Board(val board: List<MutableList<Int>>) {
    val checked: List<MutableList<Boolean>> = board.map { it.map { false }.toMutableList() }
    fun setChecked(row: Int, col: Int) {
      checked[row][col] = true
    }

    fun checkValue(value: Int) {
      board.forEachIndexed { y, row ->
        row.forEachIndexed { x, col ->
          if (col == value) {
            setChecked(y, x)
          }
        }
      }
    }

    fun sumAllUnchecked(): Int {
      return checked.flatMapIndexed { y, row ->
        row.mapIndexed { x, col ->
          if (!col) {
            board[y][x]
          } else {
            0
          }
        }
      }.sum()
    }

    fun has5(): Boolean {
      val horizontal = checked.map { row -> row.all { it } }.any { it }
      val vertical = (board.indices).map { col ->
        checked.map {
          it[col]
        }.all { it }
      }.any { it }
      return horizontal || vertical
    }
  }

  override suspend fun partOne(): String {
    val draws = lines.first().split(",").map { it.toInt() }
    println(draws)
    val boards = lines.drop(1).windowed(5, 5).map { board ->
      Board(board.map { s -> s.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList() })
    }
    draws.forEach { number ->
      boards.forEach { board ->
        board.checkValue(number)

        if (board.has5()) {
          board.checked.forEach {
            println(it)
          }
          println(board.sumAllUnchecked())
          println(board.sumAllUnchecked() * number)
          return (board.sumAllUnchecked() * number).toString()
        }
      }
    }
    return ""
  }

  override suspend fun partTwo(): String {
    val draws = lines.first().split(",").map { it.toInt() }
    println(draws)
    val boards = lines.drop(1).windowed(5, 5).map { board ->
      Board(board.map { s -> s.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList() })
    }

    val boardsWon = boards.indices.associateWith { false }.toMutableMap()
    var last = ""
    draws.forEach { number ->
      boards.forEachIndexed { index, board ->
        if (!boardsWon.getValue(index)) {
          board.checkValue(number)

          if (board.has5()) {
            boardsWon[index] = true
            board.checked.forEach {
              println(it)
            }
            board.board.forEach {
              println(it)
            }
            val sumAllUnchecked = board.sumAllUnchecked()
            last = (sumAllUnchecked * number).toString()
            println("$index $sumAllUnchecked $number $last")
          }
        }
      }
    }
    return last
  }

  companion object {
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D04)
  PuzzleRunner.run(Y2021D04(input))
  PuzzleRunner.run(Y2021D04())
}
