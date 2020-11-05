package com.kalvatn.aoc.core.model

import com.kalvatn.aoc.utils.timeit
import com.kalvatn.aoc.utils.toHMS
import kotlinx.coroutines.runBlocking
import java.time.Duration


class PartResult<T>(
  val value: T,
  val nanos: Duration = Duration.ZERO
) {
  fun string() = value.toString()
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as PartResult<*>

    if (value != other.value) return false

    return true
  }

  override fun hashCode(): Int {
    return value?.hashCode() ?: 0
  }
  companion object {
    fun <T> of(block:() -> T): PartResult<T> {
      val (result, duration) = timeit(block)
      return PartResult(result, duration)
    }
  }
}

data class Output<T1, T2>(
  val year: Year,
  val day: Day,
  val p1: PartResult<T1>,
  val p2: PartResult<T2>,
  val total:Duration = Duration.ZERO
) {
  private fun nanos(): Duration = total

  fun print() {
    println(
      """
            |${year.intString()}-${day.intString()}
            |   part one : ${p1.string().padEnd(30)} ${p1.nanos.toHMS()}
            |   part two : ${p2.string().padEnd(30)} ${p2.nanos.toHMS()}
            |${nanos().toHMS()}"""
        .trimMargin("|")
    )
  }

  companion object {
    fun <T1, T2> of(year:Year, day:Day, block:() -> Pair<PartResult<T1>, PartResult<T2>>): Output<T1, T2> = runBlocking{
      val (result, duration) = timeit {
        block()
      }
      Output(year, day, result.first, result.second, duration)
    }
  }

}
