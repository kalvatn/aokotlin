@file:Suppress("unused")

package com.kalvatn.aoc.common.graph

import java.util.ArrayDeque
import java.util.Deque
import java.util.PriorityQueue

@Suppress("FunctionName")
fun <T> Graph<T>.DFS(start: T, target: T? = null): List<T> {
  val visited = edges.keys.map { it to false }.toMap().toMutableMap()

  val stack: Deque<T> = ArrayDeque()
  val traversed: MutableList<T> = mutableListOf()

  stack.push(start)

  var i = 0
  while (stack.isNotEmpty()) {
    val current = stack.pop()
    if (!visited[current]!!) {
      i++
      traversed.add(current)
      visited[current] = true
      if (target != null && current == target) {
        println(i)
        return traversed
      }
      edges[current]?.forEach {
        stack.push(it)
      }
    }
  }

  return traversed
}

@Suppress("FunctionName")
fun <T> Graph<T>.BFS(start: T): List<T> {
  val visited = edges.keys.map { it to false }.toMap().toMutableMap()

  val queue: Deque<T> = ArrayDeque<T>()
  val traversed: MutableList<T> = mutableListOf()

  queue.add(start)
  while (queue.isNotEmpty()) {
    val current = queue.remove()
    if (!visited[current]!!) {
      traversed.add(current)
      visited[current] = true
      edges[current]?.forEach {
        queue.add(it)
      }
    }
  }
  return traversed
}

fun <T> Graph<T>.distances(start: T): Map<T, Int> {
  val visited = edges.keys.map { it to false }.toMap().toMutableMap()

  val queue: Deque<T> = ArrayDeque<T>()
  val traversed: MutableList<T> = mutableListOf()

  val distance = edges.keys.map { it to DISTANCE_INF }.toMap().toMutableMap()
  distance[start] = 0

  queue.add(start)
  while (queue.isNotEmpty()) {
    val current = queue.remove()
    if (!visited[current]!!) {
      traversed.add(current)
      visited[current] = true
      edges[current]?.forEach {
        if (distance[it] == DISTANCE_INF) {
          distance[it] = distance[current]!! + 1
          queue.add(it)
        }
      }
    }
  }
  return distance
}

fun <T> Graph<T>.shortestPath(start: T, target: T): Int {
  return distances(start)[target] ?: error("no path found")
}

private const val DISTANCE_INF = Integer.MAX_VALUE
private const val DISTANCE_ZERO = 0

fun <T> WeightedGraph<T>.dijkstra(start: T): List<T> {
  val priorityQueue: PriorityQueue<Pair<T, Int>> = PriorityQueue()
  val traversed: MutableList<T> = mutableListOf()
  val distances = edges.keys.map { it to DISTANCE_INF }.toMap().toMutableMap()

  distances[start] = DISTANCE_ZERO
  priorityQueue.add(Pair(start, distances[start]!!))

  while (priorityQueue.isNotEmpty()) {
    val (current, _) = priorityQueue.poll()
    edges[current]?.forEach { (node: T, weight: Int) ->
      if (distances[node]!! > distances[current]!! + weight) {
        priorityQueue.add(Pair(node, distances[current]!! + weight))
      }
    }
  }

  return traversed
}
