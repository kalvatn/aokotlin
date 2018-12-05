package com.kalvatn.aoc.exceptions

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Test

class ExceptionsTest {
    @Test
    fun testImpossiburu() {
        val e = Impossiburu()
        Assert.assertThat(e.message, equalTo("impossiburu"))
    }

    @Test
    fun testCookieMissing() {
        val e = CookieMissing()
        Assert.assertThat(e.message, equalTo("src/main/resources/session.cookie is missing or empty"))
    }
}