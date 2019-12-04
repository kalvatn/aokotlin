package com.kalvatn.aoc.common.graph

import java.util.*


@Suppress("FunctionName")
fun <T> Graph<T>.DFS(start: T): List<T> {
    val visited = connections.keys.map { it to false }.toMap().toMutableMap()

    val stack: Deque<T> = ArrayDeque()
    val traversed: MutableList<T> = mutableListOf()

    stack.push(start)

    while (stack.isNotEmpty()) {
        val current = stack.pop()
        if (!visited[current]!!) {
            traversed.add(current)
            visited[current] = true
            connections[current]?.forEach {
                stack.push(it)
            }
        }
    }

    return traversed
}

@Suppress("FunctionName")
fun <T> Graph<T>.BFS(start: T): List<T> {
    val visited = connections.keys.map { it to false }.toMap().toMutableMap()

    val queue: Deque<T> = ArrayDeque()
    val traversed: MutableList<T> = mutableListOf()

    queue.add(start)
    while (queue.isNotEmpty()) {
        val current = queue.remove()
        if (!visited[current]!!) {
            traversed.add(current)
            visited[current] = true
            connections[current]?.forEach {
                queue.add(it)
            }
        }

    }

    return traversed
}

private const val DISTANCE_INF = Integer.MAX_VALUE
private const val DISTANCE_ZERO = 0

fun <T> WeightedGraph<T>.dijkstra(start: T): List<T> {

    val priorityQueue: PriorityQueue<Pair<T, Int>> = PriorityQueue()
    val traversed: MutableList<T> = mutableListOf()
    val distances = connections.keys.map { it to DISTANCE_INF }.toMap().toMutableMap()

    distances[start] = DISTANCE_ZERO
    priorityQueue.add(Pair(start, distances[start]!!))

    while (priorityQueue.isNotEmpty()) {
        val (current, _) = priorityQueue.poll()
        connections[current]?.forEach { (node: T, weight: Int) ->
            if (distances[node]!! > distances[current]!! + weight) {
                priorityQueue.add(Pair(node, distances[current]!! + weight))
            }
        }
    }


    return traversed

}




