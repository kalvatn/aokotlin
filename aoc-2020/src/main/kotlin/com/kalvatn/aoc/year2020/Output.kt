package com.kalvatn.aoc.year2020

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.utils.toHMS
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
}

data class Output<T1, T2>(
  val year: Year,
  val day: Day,
  val p1: PartResult<T1>,
  val p2: PartResult<T2>
) {
  private fun nanos(): Duration = p1.nanos.plus(p2.nanos)
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

}
