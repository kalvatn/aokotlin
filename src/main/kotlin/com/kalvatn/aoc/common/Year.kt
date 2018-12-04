package com.kalvatn.aoc.common


enum class Year {
    Y2015,
    Y2016,
    Y2017,
    Y2018,
    Y2019;

    fun intValue(): Int {
        return intString().toInt()
    }

    fun intString(): String {
        return this.toString().substring(1)
    }
}