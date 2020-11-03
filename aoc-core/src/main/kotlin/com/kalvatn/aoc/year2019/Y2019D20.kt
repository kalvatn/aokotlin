package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.common.model.Direction
import com.kalvatn.aoc.common.model.Point
import com.kalvatn.aoc.common.model.print
import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking
import java.util.ArrayDeque
import java.util.PriorityQueue

class Y2019D20(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D20, input) {

  private val maze by lazy {
    val map = mutableMapOf<Point, Char>()
    this.input.lines.forEachIndexed { y, line ->
      line.forEachIndexed { x, item ->
        val p = Point(x, y)
        map[p] = item
      }
    }
    map
  }

  data class Portal(val name: String, val entrance: Point, val outer: Boolean = false)

  fun portals(maze: Map<Point, Char>): Map<String, Set<Portal>> {
    val portals = mutableMapOf<String, MutableSet<Portal>>()
    maze.filterValues { it in ('A'..'Z') }.forEach { (k, v) ->
      k.adj4().firstOrNull { maze[it] == '.' }?.let {
        val name: String = when (it) {
          k + Direction.NORTH.toPointDiff() -> "$v${maze[k + Direction.SOUTH.toPointDiff()]}"
          k + Direction.SOUTH.toPointDiff() -> "${maze[k + Direction.NORTH.toPointDiff()]}$v"
          k + Direction.EAST.toPointDiff() -> "${maze[k + Direction.WEST.toPointDiff()]}$v"
          k + Direction.WEST.toPointDiff() -> "$v${maze[k + Direction.EAST.toPointDiff()]}"
          else -> error("impossiburu")
        }
        println("$k $v $name")

        val outer = it.x == 2 || it.y == 2 || it.x == maxX - 2 || it.y == maxY - 2
        portals.computeIfAbsent(name) { mutableSetOf() }.add(Portal(name, it, outer))
      }
    }
    return portals
  }

  fun portalPoints(portals: Map<String, Set<Portal>>): Map<Point, Point> {
    val entranceToExit = mutableMapOf<Point, Point>()
    portals.filterValues { it.size == 2 }.values.forEach { it ->
      entranceToExit[it.first().entrance] = it.last().entrance
      entranceToExit[it.last().entrance] = it.first().entrance
    }
    return entranceToExit.toMap()
  }

  data class Node(
    val point: Point,
    val distance: Int = 0,
    val justExited: Boolean = false,
    val depth: Int = 0
  ) : Comparable<Node> {

    override fun compareTo(other: Node): Int {
      val d = distance.compareTo(other.distance)
      if (d == 0) {
        return depth.compareTo(other.depth)
      }
      return d
    }
  }

  fun nextNodes(node: Node, maze: Map<Point, Char>, p2p: Map<Point, Point>): Set<Node> {
    if (node.point in p2p && !node.justExited) {
      return setOf(
        Node(p2p[node.point] ?: error("impossiburu"), node.distance + 1, true)
      )
    }
    return node.point.adj4().filter { it in maze.keys }.filter { maze[it] == '.' }.map {
      val np = it
      Node(np, node.distance + 1)
    }.toSet()
  }

  fun shortestPath(maze: Map<Point, Char>, p2p: Map<Point, Point>, start: Point, target: Point): Int {
    var best = Integer.MAX_VALUE

    val queue = ArrayDeque<Node>()
    queue.add(Node(start, 0))
    val visited = mutableSetOf<Point>()

    while (queue.isNotEmpty()) {
      val current = queue.remove()

      if (current.point in visited) {
        continue
      }
      visited.add(current.point)

      if (current.point == target) {
        if (current.distance < best) {
          best = current.distance
        }
      }

      nextNodes(current, maze, p2p).forEach {
        queue.add(it)
      }
    }

    return best
  }

  override suspend fun partOne(): String {
    val maze = maze.toMap()
    maze.print {
      print(maze[it])
    }
    val portals = portals(maze)
    portals.forEach {
      println(it)
    }
    val p2p = portalPoints(portals)
    val start = (portals["AA"] ?: error("impossiburu")).first().entrance
    val target = (portals["ZZ"] ?: error("impossiburu")).first().entrance

    return shortestPath(maze, p2p, start, target).toString()
  }

  val maxX = maze.keys.maxBy { it.x }!!.x
  val maxY = maze.keys.maxBy { it.y }!!.y

  fun nextNodes2(node: Node, maze: Map<Point, Char>, p2p: Map<Point, Point>): Set<Node> {
    if (node.point in p2p && !node.justExited) {
      val outer = node.point.x == 2 || node.point.y == 2 || node.point.x == maxX - 2 || node.point.y == maxY - 2
      if (outer && node.depth > 0) {
        return setOf(
          Node(
            p2p[node.point]
              ?: error("impossiburu"),
            node.distance + 1, true, node.depth - 1
          )
        )
      } else if (!outer && node.depth <= 100) {
        return setOf(
          Node(
            p2p[node.point]
              ?: error("impossiburu"),
            node.distance + 1, true, node.depth + 1
          )
        )
      }
    }
    return node.point.adj4().filter { it in maze.keys }.filter { maze[it] == '.' }.map {
      val np = it
      Node(np, node.distance + 1, false, node.depth)
    }.toSet()
  }

  suspend fun shortestPath2(maze: Map<Point, Char>, p2p: Map<Point, Point>, start: Node, target: Node): Int {
    var best = Integer.MAX_VALUE

    val queue = PriorityQueue<Node>()
    queue.add(start)
    val visited = mutableSetOf<Pair<Point, Int>>()

    var steps = 0L
    while (queue.isNotEmpty()) {
      val current = queue.remove()

      val hash = Pair(current.point, current.depth)
      if (hash in visited) {
        continue
      }
      visited.add(hash)

//            if (steps++ % 10000L == 0L) {
//                println(current)
//            }

      if (current.point == target.point && current.depth == 0) {
        if (current.distance < best) {
          best = current.distance
          return best
        }
      }

      nextNodes2(current, maze, p2p).forEach {
        queue.add(it)
      }
    }

    return best
  }

  override suspend fun partTwo(): String {
    val maze = maze.toMap()
    maze.print {
      print(maze[it])
    }
    println("max x/y = $maxX/$maxY")
    val portals = portals(maze)
    portals.forEach {
      println(it)
    }
    val p2p = portalPoints(portals)
    val start = Node((portals["AA"] ?: error("impossiburu")).first().entrance)
    val target = Node((portals["ZZ"] ?: error("impossiburu")).first().entrance)

    return shortestPath2(maze, p2p, start, target).toString()
  }
}

fun main() = runBlocking {
  PuzzleRunner(listOf(Y2019D20())).run()
}
