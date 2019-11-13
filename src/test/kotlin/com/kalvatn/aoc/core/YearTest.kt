package com.kalvatn.aoc.core

import com.kalvatn.aoc.core.model.Year
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class YearTest {

    @Test
    fun intValue() {
        Assert.assertThat(Year.Y2015.intValue(), equalTo(2015))
    }

    @Test
    fun intString() {
        Assert.assertThat(Year.Y2015.intString(), equalTo("2015"))
    }
}