package com.kalvatn.aoc.utils

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.time.Duration

class TimerKtTest : StringSpec({

  fun lol() {
    (1..10000).forEach {
      it * 2
    }
  }

  "test timeit" {
    timeit {
      lol()
    }
  }

  "test benchmark output" {
    printBenchmark(10) {
      lol()
    }
  }

  "duration toHMS" {
    Duration.ofHours(1).toHMS() shouldBe "1h 0m 0s"
    Duration.ofMinutes(61).toHMS() shouldBe "1h 1m 0s"
    Duration.ofSeconds(1).toHMS() shouldBe "1s 0ms"
    Duration.ofMillis(1).toHMS() shouldBe "1ms 0µs"
    Duration.ofMillis(1).plusNanos(2000).toHMS() shouldBe "1ms 2µs"
    Duration.ofNanos(1000).toHMS() shouldBe "1µs"
    Duration.ofNanos(1001).toHMS() shouldBe "1µs"
    Duration.ofNanos(411390).toHMS() shouldBe "411µs"
  }
})
