package com.kalvatn.aoc.common


enum class Day {
    D01,
    D02,
    D03,
    D04,
    D05,
    D06,
    D07,
    D08,
    D09,
    D10,
    D11,
    D12,
    D13,
    D14,
    D15,
    D16,
    D17,
    D18,
    D19,
    D20,
    D21,
    D22,
    D23,
    D24,
    D25;

    fun intValue(): Int {
        return intString().toInt()
    }

    fun intString(): String {
        return this.toString().substring(1)
    }
}