package com.kalvatn.aoc.utils

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class TimerKtTest {


    fun lol() {
        (1..1000).forEach {
            it * 2
        }
    }

    @Test
    fun timeit() {
        timeit {
            lol()
        }
    }

    @Test
    fun benchmark() {
        val benchmark = benchmark(10) {
            lol()
        }
        Assert.assertThat(benchmark.contains("ms avg over 10 executions"), equalTo(true))
    }
}