package com.kalvatn.aoc.year2015

import com.kalvatn.aoc.common.Day
import com.kalvatn.aoc.common.PuzzleInput
import java.math.BigInteger
import java.security.MessageDigest

class Day04 : Day {

    constructor() : super(2015, 4)
    constructor(input: PuzzleInput) : super(2015, 4, input)

    private val key = input.singleLine()
    private val md5 = MessageDigest.getInstance("MD5")

    fun generateHash(i:Int): String {
        return BigInteger(1, md5.digest("$key$i".toByteArray())).toString(16).padStart(32, '0')
    }

    fun hasLeadingZeroes(hex: String, n: Int):Boolean {
        return hex.startsWith("0".repeat(n))
    }

    fun findSecret(n:Int): Int {
        var i = 0
        while(true) {
            val hash = generateHash(i)
            if (hasLeadingZeroes(hash, n)) {
                return i
            }
            i++
        }
    }

    override fun partOne(): String {
        return findSecret(5).toString()
    }


    override fun partTwo(): String {
        return findSecret(6).toString()
    }



}


