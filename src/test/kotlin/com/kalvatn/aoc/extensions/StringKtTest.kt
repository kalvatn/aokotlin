package com.kalvatn.aoc.extensions

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test

class StringKtTest {

    @Test
    fun extractInts() {
        Assert.assertThat("1#,adf 23,!¤!#$ @ 4 , 5678, asmb ,,, øl 9".extractIntegers(), equalTo(listOf(1, 23, 4, 5678, 9)))
        Assert.assertThat("-1#,adf 23,!¤!#$ @ -4 , 5678, asmb ,,, øl -9".extractIntegers(), equalTo(listOf(-1, 23, -4, 5678, -9)))
        Assert.assertThat("2x3x4".extractIntegers(), equalTo(listOf(2, 3, 4)))
    }

    @Test
    fun hasNVowels() {
        assertThat("aaa".hasNVowels(3), equalTo(true))
        assertThat("aeiouaeiouaeiou".hasNVowels(3), equalTo(true))
    }

    @Test
    fun hasConsecutiveLetters() {
        assertThat("abcdde".hasConsecutiveLetters(1), equalTo(true))
        assertThat("aaa".hasConsecutiveLetters(1), equalTo(true))
        assertThat("jchzalrnumimnmhp".hasConsecutiveLetters(1), equalTo(false))
    }

    @Test
    fun doesNotContain() {
        assertThat("ugknbfddgicrmopn".doesNotContain("ab", "cd", "pq", "xy"), equalTo(true))
        assertThat("aaa".doesNotContain("ab", "cd", "pq", "xy"), equalTo(true))
        assertThat("abcdde".doesNotContain("ab", "cd", "pq", "xy"), equalTo(false))
        assertThat("ab".doesNotContain("ab", "cd", "pq", "xy"), equalTo(false))
        assertThat("cd".doesNotContain("ab", "cd", "pq", "xy"), equalTo(false))
        assertThat("pq".doesNotContain("ab", "cd", "pq", "xy"), equalTo(false))
        assertThat("xy".doesNotContain("ab", "cd", "pq", "xy"), equalTo(false))
        assertThat("haegwjzuvuyypxyu".doesNotContain("ab", "cd", "pq", "xy"), equalTo(false))
    }

    @Test
    fun hasXYX() {
        Assert.assertThat("xyx".hasXYX(), equalTo(true))
        Assert.assertThat("xxx".hasXYX(), equalTo(true))
        Assert.assertThat("aaa".hasXYX(), equalTo(true))
        Assert.assertThat("qjhvhtzxzqqjkmpb".hasXYX(), equalTo(true))
        Assert.assertThat("abcdefeghi".hasXYX(), equalTo(true))
    }

    @Test
    fun hasNonOverlappingPair() {
        Assert.assertThat("qjhvhtzxzqqjkmpb".hasNonOverlappingPair(), equalTo(true))
        Assert.assertThat("uurcxstgmygtbstg".hasNonOverlappingPair(), equalTo(true))
        Assert.assertThat("xxyxx".hasNonOverlappingPair(), equalTo(true))
        Assert.assertThat("xxx".hasNonOverlappingPair(), equalTo(false))
        Assert.assertThat("aaabbb".hasNonOverlappingPair(), equalTo(false))
        Assert.assertThat("aaabbbb".hasNonOverlappingPair(), equalTo(true))
        Assert.assertThat("aaaa".hasNonOverlappingPair(), equalTo(true))
        Assert.assertThat("aaaaa".hasNonOverlappingPair(), equalTo(true))
    }
}
