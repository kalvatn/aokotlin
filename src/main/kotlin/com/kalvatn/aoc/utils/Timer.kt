package com.kalvatn.aoc.utils

import java.time.Duration


fun Duration.toHMS(): String {
    val hours = toHours()
    val minutes = minusHours(hours).toMinutes()
    val seconds = minusHours(hours).minusMinutes(minutes).toMillis() / 1000
    val millis = minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).toMillis()
    val nanos = minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).minusMillis(millis).toNanos()
    return when {
        hours > 0 -> "${hours}h ${minutes}m ${seconds}s ${millis}ms ${nanos}ns"
        minutes > 0 -> "${minutes}m ${seconds}s ${millis}ms ${nanos}ns"
        seconds > 0 -> "${seconds}s ${millis}ms ${nanos}ns"
        millis > 0 -> "${millis}ms ${nanos}ns"
        nanos > 0 -> "${nanos}ns"
        else -> "${nanos}s"
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

