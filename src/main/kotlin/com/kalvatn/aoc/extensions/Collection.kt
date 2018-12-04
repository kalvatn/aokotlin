package com.kalvatn.aoc.extensions

fun <E> Collection<E>.cycle(): Sequence<E> {
    return sequence {
        while (true) {
            for (i in this@cycle) {
                yield(i)
            }
        }
    }
}

fun <E> Collection<E>.cycle(n: Int): Sequence<E> {
    return sequence {
        repeat(n) {
            for (i in this@cycle) {
                yield(i)
            }
        }
    }
}

fun <T, R> Iterable<T>.reductions(initial: R, operation: (acc: R, T) -> R): Sequence<R> = sequence {
    yield(initial)
    var last = initial
    forEach {
        last = operation(last, it)
        yield(last)
    }
}

