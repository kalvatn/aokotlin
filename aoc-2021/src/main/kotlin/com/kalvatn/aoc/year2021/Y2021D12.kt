package com.kalvatn.aoc.year2021

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2021
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.core.runner.PuzzleRunner
import kotlinx.coroutines.runBlocking

class Graph {
  val edges = mutableMapOf<String, MutableSet<String>>()
  val nodes = mutableSetOf<String>()

  fun connect(source: String, target: String) {
    nodes.add(source)
    nodes.add(target)
    if (target != "start" || source == "end") {
      edges.computeIfAbsent(source) { mutableSetOf() }.add(target)
    }
    if (source != "start") {
      edges.computeIfAbsent(target) { mutableSetOf() }.add(source)
    }
  }

  data class Node(val name: String, val parent: Node?) {

    fun path(): List<String> {
      return if (parent == null) listOf(this.name) else parent.path() + this.name
    }

    override fun toString(): String {
      return "Node(name='$name', parent=${parent?.name})"
    }

  }

  fun pathsVisitAll(start: String, end: String): Set<List<String>> {
    val paths = mutableSetOf<List<String>>()
    val queue = ArrayDeque<Node>()
    val visited = mutableSetOf<Node>()
    queue.add(Node(start, null))
    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()
      val path = current.path()
      if (path in paths) {
        continue
      }
      if (current.name == end || path.toSet().size == nodes.toSet().size) {
        println(path)
        paths.add(path)
        continue
      }
      edges.getOrDefault(current.name, emptySet())
        .map { Node(it, parent = current) }
        .filterNot { it in visited || (it.name.first().isLowerCase() && path.contains(it.name)) }
        .forEach {
          queue.addLast(it)
        }
      visited.add(current)
    }

    return paths.toSet()

  }
}

class Y2021D12(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2021(Day.D12, input) {

  private val lines by lazy { this.input.lines }

  override suspend fun partOne(): String {
    val graph = Graph()
    lines.map {
      val (a, b) = it.split("-")
      graph.connect(a, b)
      graph.connect(b, a)
    }
    println("nodes")
    graph.nodes.forEach {
      println(it)
    }
    println("edges")
    graph.edges.forEach {
      println(it)
    }
    val paths = graph.pathsVisitAll("start", "end")
    paths.forEach {
      println(it.joinToString(","))
    }
    return paths.size.toString()
  }

  override suspend fun partTwo(): String {
    return ""
  }
}

fun main() = runBlocking {
  val input = PuzzleInput.p1Test(Year.Y2021, Day.D12)
  PuzzleRunner.run(Y2021D12(input))
  PuzzleRunner.run(Y2021D12())
}
