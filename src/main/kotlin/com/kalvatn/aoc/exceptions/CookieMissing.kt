package com.kalvatn.aoc.exceptions

import java.lang.Exception


class CookieMissing: Exception("src/main/resources/session.cookie is missing or empty")