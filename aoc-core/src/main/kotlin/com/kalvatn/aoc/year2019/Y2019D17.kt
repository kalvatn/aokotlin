package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.Turn
import com.kalvatn.aoc.common.model.print
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Y2019D17(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D17, input) {

  private val program by lazy { this.input.singleLineLongs() }

  class VaccuumRobot(private val initialPos: Point, val map: Map<Point, Char>) {

    private val scaffold = map.filter { it.value == '#' }.keys
//        private val intersections = map.keys.filter {
//            map[it] == '#' && it.adj4().all { a -> map.getOrDefault(a, '.') == '#' }
//        }

    fun print(new: Map<Point, Char>, pos: Point) {
      new.print {
        print(new[it])
      }
    }

    suspend fun moves(): List<Move> {
      var pos = initialPos
      val new = map.toMutableMap()
      val visited = mutableSetOf<Point>()
      val moves2 = mutableListOf<Move>()
      visited.add(pos)
      var count = 0
      var dir = Direction.NORTH
      var turn = Turn.LEFT

      while (scaffold.size != visited.size) {
        visited.add(pos)

        var next = pos + dir.toPointDiff()
        if (next !in scaffold) {
          if (count > 0) {
            moves2.add(Move(turn, count))
          }
          if (pos + dir.turn(Turn.LEFT).toPointDiff() in scaffold) {
            turn = Turn.LEFT
          }
          if (pos + dir.turn(Turn.RIGHT).toPointDiff() in scaffold) {
            turn = Turn.RIGHT
          }
          count = 0
//                    next = pos.adj4().filter { it in scaffold && (it !in visited || it in intersections) }.first()
//                    dir = Direction.fromPointDiff(next - pos)
//                    println(dir)
          dir = dir.turn(turn)
          next = pos + dir.toPointDiff()
        }
        count++

        new[pos] = '#'
        new[next] = 'æ'
        pos = next

//                print(new, pos)
      }
      moves2.add(Move(turn, count))

      return moves2
    }

    suspend fun move(moves: List<Move>) {
      var pos = initialPos
      var dir = Direction.NORTH
      val new = map.toMutableMap()
      val visited = mutableSetOf<Point>()
      moves.forEach {
        dir = dir.turn(it.turn)
        repeat(it.count) {
          new[pos] = '#'
          pos += dir.toPointDiff()
          new[pos] = 'æ'
          print(new, pos)
        }
      }
    }
  }

  override suspend fun partOne(): String {
    val points = mutableMapOf<Point, Char>()
    var x = 0
    var y = 0
    val pc = IntcodeComputer(program)
    pc.run()
    pc.output().forEach {
      when (it.toChar()) {
        '\n' -> {
          y += 1
          x = 0
        }
        else -> {
          points[Point(x, y)] = it.toChar()
          x++
        }
      }
    }

    val intersections = points.keys.filter {
      points[it] == '#' && it.adj4().all { a -> points.getOrDefault(a, '.') == '#' }
    }.toSet()
//        points.print {
//            if (it in intersections) {
//                print('O')
//            } else {
//                print(points[it])
//            }
//        }
    return intersections.map { it.x * it.y }.sum().toString()
  }

  override suspend fun partTwo(): String {
    val points = mutableMapOf<Point, Char>()
    var x = 0
    var y = 0
    val pc = IntcodeComputer(program)
    pc.run()
    pc.output().forEach {
      when (it.toChar()) {
        '\n' -> {
          y += 1
          x = 0
        }
        else -> {
          points[Point(x, y)] = it.toChar()
          x++
        }
      }
    }
    val intersections = points.keys.filter {
      points[it] == '#' && it.adj4().all { a -> points.getOrDefault(a, '.') == '#' }
    }.toSet()
//        points.print {
//            if (it in intersections) {
//                print('O')
//            } else {
//                print(points[it])
//            }
//        }

    val pos = points.filter { it.value == '^' }.keys.first()
    val vaccuumRobot = VaccuumRobot(pos, points)
//        var cur = Direction.NORTH
    val moves = vaccuumRobot.moves()

    println(moves)
    (2..10).forEach { ws ->
      println("window size $ws")
      moves.windowed(ws).groupingBy { it }.eachCount().filter {
        it.value > 1
      }.forEach {
        println(it)
      }
    }
//        val A = listOf<Move>()
//        val B = listOf<Move>()
//        val C = listOf<Move>()
//        val main = listOf(A, B, C)

    //TODO FIXME XXX
    val A = "L,8,R,10,L,10,\n"
    val B = "R,10,L,8,L,8,L,10,\n"
    val C = "L,4,L,6,L,8,L,8,\n"
    val D = "A,B,A,C,B,C,A,C,B,C,\n"
    val Aenc = A.map { it.toInt() }
    val Benc = B.map { it.toInt() }
    val Cenc = C.map { it.toInt() }
    val Denc = D.map { it.toInt() }
    println("$A -> $Aenc")
    println("$B -> $Benc")
    println("$C -> $Cenc")
    println("$D -> $Denc")

    pc.replaceIndex(0, 2)
    pc.clearOutput()
    listOf(Denc, Aenc, Benc, Cenc).forEach { f ->
      f.forEach {
        pc.input(it.toLong())
        pc.run()
      }
    }
    pc.input('n'.toInt().toLong())
    pc.input('\n'.toInt().toLong())
    pc.run()

    return pc.outputLast().toString()
  }

  data class Move(val turn: Turn, val count: Int) {
    override fun toString(): String {
      return "${turn.toChar()},$count"
    }
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D17())).run()
}
