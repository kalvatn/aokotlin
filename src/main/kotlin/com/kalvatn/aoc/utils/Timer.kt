package com.kalvatn.aoc.utils

import java.time.Duration


fun Duration.toHMS(): String {
    val hh = toHours()
    val mm = minusHours(hh).toMinutes()
    val ss = minusHours(hh).minusMinutes(mm).toMillis() / 1000
    val ms = minusHours(hh).minusMinutes(mm).minusSeconds(ss).toMillis()
    val ns = minusHours(hh).minusMinutes(mm).minusSeconds(ss).minusMillis(ms).toNanos()
    return when {
        hh > 0 -> "${hh}h ${mm}m ${ss}s ${ms}ms ${ns}ns"
        mm > 0 -> "${mm}m ${ss}s ${ms}ms ${ns}ns"
        ss > 0 -> "${ss}s ${ms}ms ${ns}ns"
        ms > 0 -> "${ms}ms ${ns}ns"
        ns > 0 -> "${ns}ns"
        else -> "${ns}ns"
    }
}

inline fun <T> timeit(block: () -> T): Pair<T, Duration> {
    val start = System.nanoTime()
    val result = block()
    val end = System.nanoTime()

    return Pair(result, Duration.ofNanos(end - start))
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

