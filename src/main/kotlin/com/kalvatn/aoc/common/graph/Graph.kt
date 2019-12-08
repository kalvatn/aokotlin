package com.kalvatn.aoc.common.graph

class Graph<T> {

    internal val edges = mutableMapOf<T, MutableSet<T>>()
    private val parents = mutableMapOf<T,MutableSet<T>>()

    fun connect(source: T, target: T) {
        edges.computeIfAbsent(source) { mutableSetOf() }.add(target)
        edges.computeIfAbsent(target) { mutableSetOf() }.add(source)

        parents.computeIfAbsent(target) { mutableSetOf()}.add(source)
    }

    fun parentOf(source:T) = parents[source]?.firstOrNull() ?: error("$source has no parents")

}


class WeightedGraph<T> {

    internal val edges = mutableMapOf<T, MutableSet<Pair<T, Int>>>()

    fun connect(source:T, target:T, weight:Int) {
        edges.computeIfAbsent(source) { mutableSetOf() }.add(Pair(target, weight))
        edges.computeIfAbsent(target) { mutableSetOf() }.add(Pair(source, weight))
    }
}