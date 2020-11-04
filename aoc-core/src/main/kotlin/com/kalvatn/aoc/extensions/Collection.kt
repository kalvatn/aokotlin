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

fun <K, V> Collection<Map<K, V>>.groupMapsBySharedKeys(): Map<K, List<V>> =
  foldIndexed(mapOf()) { index, acc, item ->
    when (index) {
      0 -> item.map { it.key to listOf(it.value) }.toMap()
      else -> {
        val commonKeys = acc.keys.intersect(item.keys)
        acc.filterKeys { it in commonKeys }.map {
          it.key to it.value + (item[it.key] ?: error("impossiburu"))
        }.toMap()
      }
    }
  }

//fun <K, V> List<Map<K, V>>.groupMapsBySharedKeys2(): Map<K, List<V>> =
//        flatMap { it.entries }
//                .groupBy({ it.key }, { it.value })
//                .filter { it.value.size > 1 }
