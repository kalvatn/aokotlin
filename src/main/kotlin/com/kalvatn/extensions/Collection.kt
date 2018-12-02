package com.kalvatn.extensions

fun <E> Collection<E>.cycle(): Iterator<E> {
    return generateSequence { this }.flatten().iterator()
}

fun <E> Collection<E>.cycle(n: Int): Iterator<E> {
    return generateSequence { this }.take(n).flatten().iterator()
}
