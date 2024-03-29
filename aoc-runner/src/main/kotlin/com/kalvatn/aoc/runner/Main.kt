package com.kalvatn.aoc.runner

import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.year2015.Y2015D01
import com.kalvatn.aoc.year2015.Y2015D02
import com.kalvatn.aoc.year2015.Y2015D03
import com.kalvatn.aoc.year2015.Y2015D04
import com.kalvatn.aoc.year2015.Y2015D05
import com.kalvatn.aoc.year2015.Y2015D06
import com.kalvatn.aoc.year2015.Y2015D07
import com.kalvatn.aoc.year2018.Y2018D01
import com.kalvatn.aoc.year2018.Y2018D02
import com.kalvatn.aoc.year2018.Y2018D03
import com.kalvatn.aoc.year2018.Y2018D04
import com.kalvatn.aoc.year2018.Y2018D05
import com.kalvatn.aoc.year2018.Y2018D06
import com.kalvatn.aoc.year2018.Y2018D07
import com.kalvatn.aoc.year2018.Y2018D08
import com.kalvatn.aoc.year2018.Y2018D09
import com.kalvatn.aoc.year2018.Y2018D10
import com.kalvatn.aoc.year2018.Y2018D11
import com.kalvatn.aoc.year2018.Y2018D12
import com.kalvatn.aoc.year2018.Y2018D13
import com.kalvatn.aoc.year2018.Y2018D14
import com.kalvatn.aoc.year2018.Y2018D15
import com.kalvatn.aoc.year2018.Y2018D16
import com.kalvatn.aoc.year2019.Y2019D01
import com.kalvatn.aoc.year2019.Y2019D02
import com.kalvatn.aoc.year2019.Y2019D03
import com.kalvatn.aoc.year2019.Y2019D04
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
  val puzzles = listOf(
    Y2015D01(),
    Y2015D02(),
    Y2015D03(),
    Y2015D04(),
    Y2015D05(),
    Y2015D06(),
    Y2015D07(),

    Y2018D01(),
    Y2018D02(),
    Y2018D03(),
    Y2018D04(),
    Y2018D05(),
    Y2018D06(),
    Y2018D07(),
    Y2018D08(),
    Y2018D09(),
    Y2018D10(),
    Y2018D11(),
    Y2018D12(),
    Y2018D13(),
    Y2018D14(),
    Y2018D15(),
    Y2018D16(),

    Y2019D01(),
    Y2019D02(),
    Y2019D03(),
    Y2019D04()
  )
  PuzzleRunner(puzzles).run()
}
