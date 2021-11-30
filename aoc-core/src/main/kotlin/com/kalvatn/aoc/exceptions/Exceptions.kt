package com.kalvatn.aoc.exceptions

class Impossiburu : Exception("impossiburu")
class CookieMissing : Exception("src/main/resources/session.cookie is missing or empty")
class CookieExpired : Exception("src/main/resources/session.cookie is expired")
class PrematureDownload : Exception("puzzle not unlocked yet")
