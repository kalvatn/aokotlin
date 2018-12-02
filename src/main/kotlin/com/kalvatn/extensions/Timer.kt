package com.kalvatn.extensions


inline fun timeit(block: () -> Unit) {
    val start = System.nanoTime()
    block()
    println("             ${(System.nanoTime() - start) / 1000000.0} ms")
}

//inline fun benchmark(block: () -> Unit) {
//    val runs = 10
//    val runtimes = mutableListOf<Long>()
//    for (i in 1..runs) {
//        val start = System.nanoTime()
//        block()
//        runtimes.add(System.nanoTime() - start)
//    }
//    println("             ${runtimes.average() / 1000000.0} ms avg over $runs executions")
//}
inline fun benchmark(
        ITERATIONS: Int = 100,
        TEST_COUNT: Int = 10,
        WARM_COUNT: Int = 2,
        callback: () -> Unit
) {
    val results = ArrayList<Long>()
    var totalTime = 0L
    var t = 0

    while (++t <= TEST_COUNT + WARM_COUNT) {
        val startTime = System.currentTimeMillis()

        var i = 0
        callback()

        if (t <= WARM_COUNT) {
            continue
        }

        val time = System.currentTimeMillis() - startTime

        results.add(time)
        totalTime += time
    }

    results.sort()

    val average = totalTime / TEST_COUNT
    val median = results[results.size / 2]

    println("             average=${average}ms / median=${median}ms")
}
