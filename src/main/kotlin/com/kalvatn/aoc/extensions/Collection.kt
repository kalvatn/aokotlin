package com.kalvatn.aoc.extensions

fun <E> Collection<E>.cycle(): Iterator<E> {
    return generateSequence { this }.flatten().iterator()
}

fun <E> Collection<E>.cycle(n: Int): Iterator<E> {
    return generateSequence { this }.take(n).flatten().iterator()
}

fun <T, R> Iterable<T>.reductions(initial: R, operation: (acc: R, T) -> R): Sequence<R> = sequence {
    yield(initial)
    var last = initial
    forEach {
        last = operation(last, it)
        yield(last)
    }
}

