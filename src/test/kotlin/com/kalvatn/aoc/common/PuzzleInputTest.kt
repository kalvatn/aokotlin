package com.kalvatn.aoc.common

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class PuzzleInputTest {
    @Test
    fun ofString() {
        val ofString = PuzzleInput.ofSingleLine("2x3x4")
        Assert.assertThat(ofString.lines[0], equalTo("2x3x4"))
    }

}