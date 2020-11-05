package com.kalvatn.aoc.year2020

import java.io.File

class FileInput(
  private val file: File
) : Input {
  override fun toString(): String {
    return file.name
  }

  override fun lines(): List<String> {
    return file.readLines()
  }


}
