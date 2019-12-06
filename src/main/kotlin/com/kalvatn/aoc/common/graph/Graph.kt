package com.kalvatn.aoc.common.graph

class Graph<T> {

    internal val connections = mutableMapOf<T, MutableSet<T>>()
    internal val parents = mutableMapOf<T,MutableSet<T>>()

    fun connect(source: T, target: T) {
        connections.computeIfAbsent(source) { mutableSetOf() }.add(target)
        connections.computeIfAbsent(target) { mutableSetOf() }.add(source)

        parents.computeIfAbsent(target) { mutableSetOf()}.add(source)
    }

}


class WeightedGraph<T> {

    internal val connections = mutableMapOf<T, MutableSet<Pair<T, Int>>>()

    fun connect(source:T, target:T, weight:Int) {
        connections.computeIfAbsent(source) { mutableSetOf() }.add(Pair(target, weight))
        connections.computeIfAbsent(target) { mutableSetOf() }.add(Pair(source, weight))
    }
}