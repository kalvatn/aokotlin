package com.kalvatn.aoc.common.graph

import java.util.ArrayDeque

class Graph<T> {

  internal val edges = mutableMapOf<T, MutableSet<T>>()
  private val parents = mutableMapOf<T, MutableSet<T>>()

  fun connect(source: T, target: T) {
    edges.computeIfAbsent(source) { mutableSetOf() }.add(target)
    edges.computeIfAbsent(target) { mutableSetOf() }.add(source)

    parents.computeIfAbsent(target) { mutableSetOf() }.add(source)
  }

  fun parentOf(source: T) = parents[source]?.firstOrNull() ?: error("$source has no parents")
}

class WeightedGraph<T> {

  val edges = mutableMapOf<T, MutableMap<T, Int>>()

  fun connect(source: T, target: T, weight: Int) {
    edges.computeIfAbsent(source) { mutableMapOf() }.computeIfAbsent(target) { weight }
    edges.computeIfAbsent(target) { mutableMapOf() }.computeIfAbsent(source) { weight }
  }

  data class Path<T>(val path: List<T>, val distance: Int)

  data class Node<T>(val value: T, val distance: Int, val parent: Node<T>? = null)

  private tailrec fun <T> path(node: Node<T>, path: Path<T> = Path(listOf(), 0)): Path<T> {
    if (node.parent == null) {
      return Path(path.path + node.value, path.distance + node.distance)
    }
    return path(node.parent, Path(path.path + node.value, path.distance + node.distance))
  }

  fun shortestVisitAll(start: T, first: Boolean = true): Path<T> {
    var best = Int.MAX_VALUE

    val paths = mutableListOf<Path<T>>()
    val queue = ArrayDeque<Node<T>>()
    val visited = mutableSetOf<Node<T>>()
    queue.add(Node(start, 0))
    while (queue.isNotEmpty()) {
      val current = queue.remove()
      val path = path(current)
      if (path in paths || path.path.size > edges.keys.size || path.distance > best) {
        continue
      }
      if (path.path.toSet() == edges.keys) {
        if (path.distance <= best) {
          best = path.distance
          paths.add(path)
          if (first) {
            return paths.first()
          }
        }
      }
      if (current in visited) {
        continue
      }
      visited.add(current)
      edges.getOrDefault(current.value, mutableMapOf()).forEach { (t, u) ->
        queue.add(Node(t, u, current))
      }
    }

    return paths.minByOrNull { it.distance } ?: error("path not found")
  }

  fun longestVisitAll(start: T, first: Boolean = true): Path<T> {
    var worst = 0

    val paths = mutableListOf<Path<T>>()
    val queue = ArrayDeque<Node<T>>()
    val visited = mutableSetOf<Node<T>>()
    queue.add(Node(start, 0))
    while (queue.isNotEmpty()) {
      val current = queue.remove()
      val path = path(current)
      if (path in paths || path.path.size > edges.keys.size || path.distance < worst) {
        continue
      }
      if (path.path.toSet() == edges.keys) {
        if (path.distance >= worst) {
          worst = path.distance
          paths.add(path)
          if (first) {
            return paths.first()
          }
        }
      }
      if (current in visited) {
        continue
      }
      visited.add(current)
      edges.getOrDefault(current.value, mutableMapOf()).forEach { (t, u) ->
        queue.add(Node(t, u, current))
      }
    }

    return paths.maxByOrNull { it.distance } ?: error("path not found")
  }
}
