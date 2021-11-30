package com.kalvatn.aoc.common.graph

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
