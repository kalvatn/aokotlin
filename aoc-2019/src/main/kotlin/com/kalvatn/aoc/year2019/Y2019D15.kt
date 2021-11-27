package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.graph.Graph
import com.kalvatn.aoc.common.graph.shortestPath
import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import java.util.ArrayDeque

class Y2019D15(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D15, input) {

  private val program by lazy { this.input.singleLineLongs() }

  private val pc = IntcodeComputer(program)

  private val repairDroid by lazy {
    RepairDroid(pc).also {
      it.move()
      it.draw()
    }
  }

  enum class Status(val value: Long) {
    HIT_WALL(0),
    MOVED(1),
    FOUND_OXYGEN_SYSTEM(2);

    companion object {
      private val BY_ID = values().associateBy { it.value }
      fun fromId(value: Long): Status = BY_ID[value] ?: error("no status with value $value")
    }
  }

  class RepairDroid(private val computer: IntcodeComputer) {

    private var curDir = Direction.NORTH
    private var curPos = Point(0, 0)
    val points = mutableMapOf<Point, Status>()
    private val pointsVisit = mutableMapOf<Point, Int>()
    private val walls = mutableSetOf<Point>()
    private var i = 0
    private var osFound = false

    private fun isFullyMapped(): Boolean {
      if (!osFound) {
        return false
      }

      val (xMin, xMax, yMin, yMax) = with(points.keys) {
        listOf(
          minByOrNull { it.x }!!.x,
          maxByOrNull { it.x }!!.x,
          minByOrNull { it.y }!!.y,
          maxByOrNull { it.y }!!.y
        )
      }
      (yMin + 1 until yMax).forEach { y ->
        (xMin + 1 until xMax).forEach { x ->
          val p = Point(x, y)
          if (!points.containsKey(p)) {
            if (!p.adj4().all { points.containsKey(it) }) {
              return false
            } else {
              if (!p.adj4().all { points[it] == Status.HIT_WALL }) {
                return false
              }
            }
          }
        }
      }
      return true
    }

    fun move() {
      points[curPos] = Status.MOVED
      loop@ while (!isFullyMapped()) {
        i++
        pointsVisit[curPos] = pointsVisit.computeIfAbsent(curPos) { 0 }.inc()
        if (walls.contains(curPos + curDir.toPointDiff()) || pointsVisit.computeIfAbsent(curPos) { 0 } > 4) {
          curDir = Direction.values().map {
            val new = curPos + it.toPointDiff()
            if (!walls.contains(new)) {
              it to pointsVisit.computeIfAbsent(new) { 0 }
            } else {
              it to Int.MAX_VALUE
            }
          }.minByOrNull { it.second }!!.first
        }

        computer.input(curDir.toLong())
        computer.run()
        when (Status.fromId(computer.outputLast())) {
          Status.MOVED -> {
            curPos += curDir.toPointDiff()
            points[curPos] = Status.MOVED
          }
          Status.HIT_WALL -> {
            points[curPos + curDir.toPointDiff()] = Status.HIT_WALL
            walls.add(curPos + curDir.toPointDiff())
            curDir = Direction.values().filter { it != curDir }.map {
              val new = curPos + it.toPointDiff()
              if (!walls.contains(new)) {
                it to pointsVisit.computeIfAbsent(new) { 0 }
              } else {
                it to Int.MAX_VALUE
              }
            }.minByOrNull { it.second }!!.first
          }
          Status.FOUND_OXYGEN_SYSTEM -> {
            curPos += curDir.toPointDiff()
            points[curPos] = Status.FOUND_OXYGEN_SYSTEM
            osFound = true
          }
        }

        computer.clearOutput()
      }
    }

    fun draw() {
      val (xMin, xMax, yMin, yMax) = with(points.keys) {
        listOf(
          minByOrNull { it.x }!!.x,
          maxByOrNull { it.x }!!.x,
          minByOrNull { it.y }!!.y,
          maxByOrNull { it.y }!!.y
        )
      }
      (yMin..yMax).forEach { y ->
        (xMin..xMax).forEach { x ->
          val p = Point(x, y)
          when {
            p == curPos -> print('D')
            p == Point(0, 0) -> print('Z')
            points[p] == Status.HIT_WALL -> print('#')
            points[p] == Status.FOUND_OXYGEN_SYSTEM -> print('O')
            points[p] == Status.MOVED -> print('.')
            else -> print(' ')
          }
        }
        println()
      }
    }
  }

  override suspend fun partOne(): String {
    val points = repairDroid.points
    val os = points.filterValues { it == Status.FOUND_OXYGEN_SYSTEM }.keys.first()
    val graph: Graph<Point> = Graph()
    points.filterValues { it != Status.HIT_WALL }.forEach { (k, _) ->
      k.adj4().forEach {
        if (points.containsKey(it) && points[it]!! != Status.HIT_WALL) {
          graph.connect(k, it)
        }
      }
    }
    return graph.shortestPath(Point(0, 0), os).toString()
  }

  override suspend fun partTwo(): String {
    var i = 0

    val points = repairDroid.points
    val os = points.filterValues { it == Status.FOUND_OXYGEN_SYSTEM }.keys.first()
    val queue = ArrayDeque<List<Point>>()
    queue.add(listOf(os))
    while (queue.isNotEmpty()) {
      if (points.filter { it.value == Status.MOVED }.isEmpty()) {
        break
      }
      val cur = queue.remove()
      val adj = cur.map { it.adj4() }.flatten().filter { points.containsKey(it) && points[it] == Status.MOVED }
      queue.add(adj)
      adj.forEach {
        points[it] = Status.FOUND_OXYGEN_SYSTEM
      }
      i++
    }

    return "$i"
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D15())).run()
}
