package com.kalvatn.aoc.core.model

interface ParsedInput

class NoState


interface Task<T1, T2, T3> {
  fun year(): Year
  fun day(): Day
  suspend fun p1(state: T3): PartResult<T1>
  suspend fun p2(state: T3): PartResult<T2>
  suspend fun p1(input: Input): PartResult<T1>
  suspend fun p2(input: Input): PartResult<T2>
  suspend fun p1(input: ParsedInput): PartResult<T1>
  suspend fun p2(input: ParsedInput): PartResult<T2>
  suspend fun solve(input: Input): Output<T1, T2>
}

abstract class AbstractTask<T1, T2, T3 : Any>(val year: Year, val day: Day) : Task<T1, T2, T3> {
  override fun year() = year
  override fun day() = day
  override suspend fun p1(state: T3): PartResult<T1> {
    throw NotImplementedError()
  }

  override suspend fun p2(state: T3): PartResult<T2> {
    throw NotImplementedError()
  }

  override suspend fun p1(input: Input): PartResult<T1> {
    throw NotImplementedError()
  }

  override suspend fun p2(input: Input): PartResult<T2> {
    throw NotImplementedError()
  }

  override suspend fun p1(input: ParsedInput): PartResult<T1> {
    throw NotImplementedError()
  }

  override suspend fun p2(input: ParsedInput): PartResult<T2> {
    throw NotImplementedError()
  }


}
