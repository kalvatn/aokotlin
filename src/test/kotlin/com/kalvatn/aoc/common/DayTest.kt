package com.kalvatn.aoc.common

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class DayTest {

    @Test
    fun intValue() {
        Assert.assertThat(Day.D01.intValue(), equalTo(1))
    }

    @Test
    fun intString() {
        Assert.assertThat(Day.D01.intString(), equalTo("01"))
    }
}