package com.kalvatn.aoc.core.input

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.exceptions.CookieMissing
import okhttp3.OkHttpClient
import okhttp3.Request

object PuzzleInputDownloader {

  private val sessionCookieFile = PuzzleInputDownloader::class.java.getResource("/session.cookie")
  private val client = OkHttpClient()

  fun downloadInput(year: Year, day: Day): String {
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
