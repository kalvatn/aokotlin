package com.kalvatn.aoc.core.input

import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.Year
import com.kalvatn.aoc.exceptions.CookieExpired
import com.kalvatn.aoc.exceptions.CookieMissing
import com.kalvatn.aoc.exceptions.Impossiburu
import com.kalvatn.aoc.exceptions.PrematureDownload
import mu.KotlinLogging
import okhttp3.OkHttpClient
import okhttp3.Request

object PuzzleInputDownloader {

  private const val ERROR_LOGIN = "Puzzle inputs differ by user.  Please log in to get your puzzle input."
  private const val ERROR_PREMATURE = "Please don't repeatedly request this endpoint before it unlocks! " +
    "The calendar countdown is synchronized with the server time; " +
    "the link will be enabled on the calendar the instant this puzzle becomes available."

  private val log = KotlinLogging.logger { }
  private val sessionCookieFile = PuzzleInputDownloader::class.java.getResource("/session.cookie")
  private val client = OkHttpClient()

  fun downloadInput(year: Year, day: Day): String {
    val url = "https://adventofcode.com/${year.intValue()}/day/${day.intValue()}/input"
    if (sessionCookieFile == null) {
      throw CookieMissing()
    }
    val sessionCookie = sessionCookieFile.readText()
    val request = Request.Builder()
      .url(url)
      .addHeader("Cookie", "session=$sessionCookie")
      .build()
    log.info("downloading $year $day input from $url, request: $request")
    client.newCall(request).execute().use {
      val body = it.body ?: throw Impossiburu()
      return body.string().let { responseText ->
        when (responseText.trim()) {
          ERROR_LOGIN -> {
            log.error(responseText)
            throw CookieExpired()
          }
          ERROR_PREMATURE -> {
            log.error(responseText)
            throw PrematureDownload()
          }
          else -> responseText
        }
      }
    }
  }
}
