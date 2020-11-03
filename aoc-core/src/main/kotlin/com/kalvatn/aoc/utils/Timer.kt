package com.kalvatn.aoc.utils

import java.time.Duration

const val BENCHMARK_EXECUTIONS_DEFAULT = 10
const val ONE_SECOND_IN_MILLIS = 1000

fun Duration.toHMS(): String {
  val hh = toHours()
  val mm = minusHours(hh).toMinutes()
  val ss = minusHours(hh).minusMinutes(mm).toMillis() / ONE_SECOND_IN_MILLIS
  val ms = minusHours(hh).minusMinutes(mm).minusSeconds(ss).toMillis()
  val us = minusHours(hh).minusMinutes(mm).minusSeconds(ss).minusMillis(ms).toNanos() / ONE_SECOND_IN_MILLIS
  val ns = toNanos()
  return when {
    hh > 0 -> "${hh}h ${mm}m ${ss}s"
    mm > 0 -> "${mm}m ${ss}s"
    ss > 0 -> "${ss}s ${ms}ms"
    ms > 0 -> "${ms}ms ${us}µs"
    us > 0 -> "${us}µs"
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

inline fun benchmark(executions: Int = BENCHMARK_EXECUTIONS_DEFAULT, block: () -> Unit): String {
  val runtimes = mutableListOf<Long>()
  repeat(executions) {
    val start = System.nanoTime()
    block()
    runtimes.add(System.nanoTime() - start)
  }
  val avg = Duration.ofNanos(runtimes.average().toLong())
  val med = Duration.ofNanos(runtimes[runtimes.size / 2])
  return "$executions executions, ${avg.toHMS()} average, ${med.toHMS()} median"
}

inline fun printBenchmark(executions: Int = BENCHMARK_EXECUTIONS_DEFAULT, block: () -> Unit) {
  val s = benchmark(executions) {
    block()
  }
  println(s)
}
