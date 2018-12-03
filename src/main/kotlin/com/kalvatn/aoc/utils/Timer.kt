package com.kalvatn.aoc.utils


inline fun timeit(block: () -> Unit) {
    val start = System.nanoTime()
    block()
    println("             ${(System.nanoTime() - start) / 1000000.0} ms")
}

inline fun benchmark(executions: Int = 10, block: () -> Unit) {
    val runtimes = mutableListOf<Long>()
    repeat(executions) {
        val start = System.nanoTime()
        block()
        runtimes.add(System.nanoTime() - start)
    }
    println("             ${runtimes.average() / 1000000.0} ms avg over $executions executions")
}

