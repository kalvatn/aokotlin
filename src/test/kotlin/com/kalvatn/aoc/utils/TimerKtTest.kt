package com.kalvatn.aoc.utils

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class TimerKtTest : StringSpec({

    fun lol() {
        (1..1000).forEach {
            it * 2
        }
    }

    "test timeit" {
        timeit {
            lol()
        }
    }


    "test benchmark output" {
        val benchmark = benchmark(10) {
            lol()
        }
        benchmark.contains("ms avg over 10 executions") shouldBe true
    }
})