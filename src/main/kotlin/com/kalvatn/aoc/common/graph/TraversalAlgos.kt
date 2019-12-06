package com.kalvatn.aoc.common.graph

import java.util.*



@Suppress("FunctionName")
fun <T> Graph<T>.DFS(start: T, target:T? = null): List<T> {
    val visited = connections.keys.map { it to false }.toMap().toMutableMap()

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
            connections[current]?.forEach {
                stack.push(it)
            }
        }
    }

    return traversed
}


@Suppress("FunctionName")
fun <T> Graph<T>.BFS(start: T, target:T? = null): List<T> {
    val visited = connections.keys.map { it to false }.toMap().toMutableMap()

    val queue: Deque<T> = ArrayDeque()
    val traversed: MutableList<T> = mutableListOf()

    val distance = connections.keys.map { it to DISTANCE_INF }.toMap().toMutableMap()
    distance[start] = 0

    queue.add(start)
    while (queue.isNotEmpty()) {
        val current = queue.remove()
        if (!visited[current]!!) {
            traversed.add(current)
            visited[current] = true
            connections[current]?.forEach {
                if (distance[it] == DISTANCE_INF) {
                    distance[it] = distance[current]!! + 1
                    queue.add(it)
                }
            }
        }

    }
    println(distance)
    println(distance[target])

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

