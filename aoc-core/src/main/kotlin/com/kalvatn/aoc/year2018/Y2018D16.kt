package com.kalvatn.aoc.year2018

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2018
import com.kalvatn.aoc.extensions.extractIntegers
import kotlinx.coroutines.runBlocking

class Y2018D16(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2018(Day.D16, input) {

  interface Operation {
    fun apply(a: Int, b: Int, c: Int)
  }

  class Addr : Operation {
    /**
     addr (add register) stores into register C the result of adding register A and register B.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!! + register[b]!!
    }
  }

  class Addi : Operation {

    /**
     addi (add immediate) stores into register C the result of adding register A and value B.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!! + b
    }
  }

  class Mulr : Operation {

    /**
     mulr (multiply register) stores into register C the result of multiplying register A and register B.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!! * register[b]!!
    }
  }

  class Muli : Operation {

    /**
     muli (multiply immediate) stores into register C the result of multiplying register A and value B.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!! * b
    }
  }

  class Banr : Operation {

    /**
     banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!!.and(register[b]!!)
    }
  }

  class Bani : Operation {

    /**
     bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!!.and(b)
    }
  }

  class Borr : Operation {

    /**
     borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!!.or(register[b]!!)
    }
  }

  class Bori : Operation {

    /**
     bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!!.or(b)
    }
  }

  class Setr : Operation {

    /**
     setr (set register) copies the contents of register A into register C. (Input B is ignored.)
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = register[a]!!
    }
  }

  class Seti : Operation {

    /**
     seti (set immediate) stores value A into register C. (Input B is ignored.)
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = a
    }
  }

  class Gtir : Operation {
    /**
     gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B.
     Otherwise, register C is set to 0.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = if (a > register[b]!!) 1 else 0
    }
  }

  class Gtri : Operation {

    /**
     gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B.
     Otherwise, register C is set to 0.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = if (register[a]!! > b) 1 else 0
    }
  }

  class Gtrr : Operation {
    /**
     gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B.
     Otherwise, register C is set to 0.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = if (register[a]!! > register[b]!!) 1 else 0
    }
  }

  class Eqir : Operation {

    /**
     eqir (equal immediate/register) sets register C to 1 if value A is equal to register B.
     Otherwise, register C is set to 0.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = if (a == register[b]!!) 1 else 0
    }
  }

  class Eqri : Operation {

    /**
     eqri (equal register/immediate) sets register C to 1 if register A is equal to value B.
     Otherwise, register C is set to 0.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = if (register[a]!! == b) 1 else 0
    }
  }

  class Eqrr : Operation {

    /**
     eqrr (equal register/register) sets register C to 1 if register A is equal to register B.
     Otherwise, register C is set to 0.
     */
    override fun apply(a: Int, b: Int, c: Int) {
      register[c] = if (register[a]!! == register[b]!!) 1 else 0
    }
  }

  @Suppress("UNUSED_VARIABLE")
  override suspend fun partOne(): String {
    val operations: List<Operation> = listOf(
      Addr(),
      Addi(),
      Mulr(),
      Muli(),
      Banr(),
      Bani(),
      Borr(),
      Bori(),
      Setr(),
      Seti(),
      Gtir(),
      Gtri(),
      Gtrr(),
      Eqir(),
      Eqri(),
      Eqrr()

    )
    var totalCount = 0
    this.input.lines.windowed(3, 3, false).forEach {
      if (it[0].startsWith("Before")) {
        val (br0, br1, br2, br3) = it[0].extractIntegers()
        val (opcode, a, b, c) = it[1].extractIntegers()
        val (ar0, ar1, ar2, ar3) = it[2].extractIntegers()

        var count = 0
        operations.forEach { op ->
          register[0] = br0
          register[1] = br1
          register[2] = br2
          register[3] = br3
          op.apply(a, b, c)
          if (ar0 == register[0] && ar1 == register[1] && ar2 == register[2] && ar3 == register[3]) {
            count++
//                        println("$opcode could be ${it.javaClass.simpleName}")
          }
        }

        if (count >= 3) {
          totalCount += 1
        }
      }
    }
    return totalCount.toString()
  }

  override suspend fun partTwo(): String {
    return ""
  }

  companion object {
    val register = mutableMapOf<Int, Int>()
  }
}

fun main() = runBlocking {
  Y2018D16().run()
}
