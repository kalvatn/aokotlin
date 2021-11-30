package com.kalvatn.aoc.core.model

@Suppress("unused")
enum class Year {
  Y2015,
  Y2016,
  Y2017,
  Y2018,
  Y2019,
  Y2020,
  Y2021,
  ;

  fun intValue(): Int {
    return intString().toInt()
  }

  fun intString(): String {
    return toString().substring(1)
  }
}
