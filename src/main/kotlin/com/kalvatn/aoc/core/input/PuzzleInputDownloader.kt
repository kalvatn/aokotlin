package com.kalvatn.aoc.core.input

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.exceptions.CookieMissing
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File


object PuzzleInputDownloader {
    private val sessionCookieFile = File("src/main/resources/session.cookie")
    private val client = OkHttpClient()

    fun downloadInput(year: Year, day: Day) : String {
        val url = "https://adventofcode.com/${year.intValue()}/day/${day.intValue()}/input"
        val sessionCookie = sessionCookieFile.readText()
        if (sessionCookie.isBlank()) {
            throw CookieMissing()
        }
        val request = Request.Builder()
                .url(url)
                .addHeader("Cookie", "session=$sessionCookie")
                .build()
        client.newCall(request).execute().use {
            return it.body!!.string()
        }
    }
}

fun main() {
    val input = PuzzleInputDownloader.downloadInput(Year.Y2015, Day.D01)
    println(input)
}