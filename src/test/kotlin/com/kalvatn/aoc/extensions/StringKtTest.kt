package com.kalvatn.aoc.extensions

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class StringKtTest {

    @Test
    fun extractInts() {
        Assert.assertThat("1#,adf 23,!¤!#$ @ 4 , 5678, asmb ,,, øl 9".extractIntegers(), equalTo(listOf(1, 23, 4, 5678, 9)))
        Assert.assertThat("-1#,adf 23,!¤!#$ @ -4 , 5678, asmb ,,, øl -9".extractIntegers(), equalTo(listOf(-1, 23, -4, 5678, -9)))
        Assert.assertThat("2x3x4".extractIntegers(), equalTo(listOf(2, 3, 4)))
    }
}