package com.kalvatn.aoc.extensions

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringKtTest : StringSpec({

  "extract integers" {
    "1#,adf 23,!¤!#$ @ 4 , 5678, asmb ,,, øl 9".extractIntegers() shouldBe listOf(1, 23, 4, 5678, 9)
    "-1#,adf 23,!¤!#$ @ -4 , 5678, asmb ,,, øl -9".extractIntegers() shouldBe listOf(-1, 23, -4, 5678, -9)
    "2x3x4".extractIntegers() shouldBe listOf(2, 3, 4)
  }

  "has n wovels" {
    "aaa".hasNVowels() shouldBe true
    "aeiouaeiouaeiou".hasNVowels(3) shouldBe true
    "aaa".hasNVowels() shouldBe true
    "aeiouaeiouaeiou".hasNVowels(3) shouldBe true
  }

  "has consecutive letters" {
    "abcdde".hasConsecutiveLetters(1) shouldBe true
    "aaa".hasConsecutiveLetters(1) shouldBe true
    "jchzalrnumimnmhp".hasConsecutiveLetters(1) shouldBe false
    "abcdde".hasConsecutiveLetters() shouldBe true
    "aaa".hasConsecutiveLetters() shouldBe true
    "jchzalrnumimnmhp".hasConsecutiveLetters() shouldBe false
  }

  "does not contain" {
    "ugknbfddgicrmopn".doesNotContain("ab", "cd", "pq", "xy") shouldBe true
    "aaa".doesNotContain("ab", "cd", "pq", "xy") shouldBe true
    "abcdde".doesNotContain("ab", "cd", "pq", "xy") shouldBe false
    "ab".doesNotContain("ab", "cd", "pq", "xy") shouldBe false
    "cd".doesNotContain("ab", "cd", "pq", "xy") shouldBe false
    "pq".doesNotContain("ab", "cd", "pq", "xy") shouldBe false
    "xy".doesNotContain("ab", "cd", "pq", "xy") shouldBe false
    "haegwjzuvuyypxyu".doesNotContain("ab", "cd", "pq", "xy") shouldBe false
  }

  "has xyx" {
    "xyx".hasXYX() shouldBe true
    "xxx".hasXYX() shouldBe true
    "aaa".hasXYX() shouldBe true
    "qjhvhtzxzqqjkmpb".hasXYX() shouldBe true
    "abcdefeghi".hasXYX() shouldBe true
  }

  "has non overlapping pair" {
    "qjhvhtzxzqqjkmpb".hasNonOverlappingPair() shouldBe true
    "uurcxstgmygtbstg".hasNonOverlappingPair() shouldBe true
    "xxyxx".hasNonOverlappingPair() shouldBe true
    "xxx".hasNonOverlappingPair() shouldBe false
    "aaabbb".hasNonOverlappingPair() shouldBe false
    "aaabbbb".hasNonOverlappingPair() shouldBe true
    "aaaa".hasNonOverlappingPair() shouldBe true
    "aaaaa".hasNonOverlappingPair() shouldBe true
  }
})
