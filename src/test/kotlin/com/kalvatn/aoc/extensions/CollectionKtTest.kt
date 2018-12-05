package com.kalvatn.aoc.extensions

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test

class CollectionKtTest {
    @Test
    fun cycleN() {
        assertThat(listOf(1, 2, 3).cycle(3).toList(), equalTo(listOf(1, 2, 3, 1, 2, 3, 1, 2, 3)))
        assertThat(listOf("ab", "lol", "foo").cycle(1).toList(), equalTo(listOf("ab", "lol", "foo")))
        assertThat(listOf("ab", "lol", "foo").cycle(2).toList(), equalTo(listOf("ab", "lol", "foo", "ab", "lol", "foo")))
    }

    @Test
    fun cycle() {
        assertThat(listOf(1, 2, 3).cycle().take(10).toList(), equalTo(listOf(1, 2, 3, 1, 2, 3, 1, 2, 3, 1)))
    }

    @Test
    fun reductions() {
        val r = listOf(1, 2, 3).reductions(0) { acc, i ->
            acc + i
        }.toList()
        Assert.assertThat(r, equalTo(listOf(0, 1, 3, 6)))
    }
}