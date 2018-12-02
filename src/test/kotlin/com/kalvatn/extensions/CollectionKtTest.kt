package com.kalvatn.extensions

import org.hamcrest.CoreMatchers.*
import org.junit.Test

import org.junit.Assert.*

class CollectionKtTest {
    @Test
    fun cycle() {
        val collect = mutableListOf<Int>()
        listOf(1,2,3).cycle(3).forEach {
            collect.add(it)
        }
        assertThat(collect, equalTo(listOf(1,2,3,1,2,3,1,2,3)))
    }

}