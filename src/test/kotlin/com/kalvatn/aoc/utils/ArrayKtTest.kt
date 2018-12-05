package com.kalvatn.aoc.utils

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class ArrayKtTest {

    @Test
    fun intArray2D() {
        val a = intArray2D(SIZE)
        Assert.assertThat(a.size, equalTo(SIZE))
        Assert.assertThat(a[0][0], equalTo(0))
    }

    @Test
    fun intArray2DWithDefault() {
        val a = intArray2D(SIZE, 1)
        Assert.assertThat(a[0][0], equalTo(1))
    }

    @Test
    fun boolArray2D() {
        val a = boolArray2D(SIZE)
        Assert.assertThat(a.size, equalTo(SIZE))
        Assert.assertThat(a[0][0], equalTo(false))
    }

    @Test
    fun boolArray2DWithDefault() {
        val a = boolArray2D(SIZE, true)
        Assert.assertThat(a[0][0], equalTo(true))
    }

    @Test
    fun stringArray2D() {
        val a = stringArray2D(SIZE)
        Assert.assertThat(a.size, equalTo(SIZE))
        Assert.assertThat(a[0][0], equalTo(""))
    }

    @Test
    fun stringArray2DWithDefault() {
        val a = stringArray2D(SIZE, "lol")
        Assert.assertThat(a[0][0], equalTo("lol"))
    }

    @Test
    fun buildArray2D() {
        val a = buildArray2D(SIZE, Pair(0, 1))
        Assert.assertThat(a.size, equalTo(SIZE))
        Assert.assertThat(a[0][0], equalTo(Pair(0, 1)))
    }

    companion object {
        private const val SIZE = 10
    }
}