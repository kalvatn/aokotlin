package com.kalvatn.aoc.utils


inline fun timeit(block: () -> Unit): String {
    val start = System.nanoTime()
    block()
    val s = "             ${(System.nanoTime() - start) / 1000000.0} ms"
    println(s)
    return s
}

inline fun benchmark(executions: Int = 10, block: () -> Unit): String {
    val runtimes = mutableListOf<Long>()
    repeat(executions) {
        val start = System.nanoTime()
        block()
        runtimes.add(System.nanoTime() - start)
    }
    val s = "             ${runtimes.average() / 1000000.0} ms avg over $executions executions"
    println(s)
    return s
}

