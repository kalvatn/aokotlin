package com.kalvatn.aoc.year2020

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.stream.Stream

@ExperimentalCoroutinesApi
internal class Y2020D01Test {

  private fun puzzle() = Y2020D01()

  private companion object {
    @JvmStatic
    fun examples(): Stream<Task> {
      val p1Case1 = Input.p1Test(Year.Y2020, Day.D01, 1)
      return Arrays.stream(
        arrayOf(
          Task(p1Case1, Output(Year.Y2020, Day.D01, PartResult(138), PartResult(1771))),
          Task(p1Case1, Output(Year.Y2020, Day.D01, PartResult(138), PartResult(1771)))
        )
      )
    }
  }

  data class Task(
    val input: Input,
    val result: Output<Int, Int>
  ) {
    override fun toString(): String {
      return input.toString()
    }
  }

  @ParameterizedTest
  @MethodSource("examples")
  fun solve(task: Task) = runBlockingTest {
    puzzle().solve(task.input) shouldBe task.result
  }
}
