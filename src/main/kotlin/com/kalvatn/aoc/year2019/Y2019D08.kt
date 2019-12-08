package com.kalvatn.aoc.year2019

import com.kalvatn.aoc.core.input.PuzzleInput
import com.kalvatn.aoc.core.model.Day
import com.kalvatn.aoc.core.model.GenericPuzzle2019
import com.kalvatn.aoc.core.runner.PuzzleRunner
import com.kalvatn.aoc.utils.intArray2D
import com.kalvatn.aoc.utils.print
import kotlinx.coroutines.runBlocking
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


class Y2019D08(input: PuzzleInput = PuzzleInput.NULL) : GenericPuzzle2019(Day.D08, input) {

    private val layers by lazy { this.input.singleLine().chunked(HEIGHT * WIDTH).map { layer -> layer.map { Character.getNumericValue(it) } } }

    override suspend fun partOne(): String {
        val layerWithFewestZeros = layers.minBy { it.count { number -> number == 0 } }!!
        val ones = layerWithFewestZeros.count { it == 1 }
        val twos = layerWithFewestZeros.count { it == 2 }
        return (ones * twos).toString()
    }

    override suspend fun partTwo(): String {
        val layerRows = layers.map {
            it.chunked(WIDTH)
        }

        val image = intArray2D(HEIGHT, WIDTH, 0)
        for (y in 0 until HEIGHT) {
            for (x in 0 until WIDTH) {
                image[y][x] = layerRows.first { it[y][x] != COLOR_TRANSPARENT }[y][x]
            }
        }

        val pixelImage = BufferedImage(WIDTH * WIDTH_SCALE_FACTOR, HEIGHT * HEIGHT_SCALE_FACTOR, BufferedImage.TYPE_INT_RGB);
        for (y in 0 until HEIGHT * HEIGHT_SCALE_FACTOR) {
            for (x in 0 until WIDTH * WIDTH_SCALE_FACTOR) {
                if (image[y / HEIGHT_SCALE_FACTOR][x / WIDTH_SCALE_FACTOR] == 1) {
                    pixelImage.setRGB(x, y, Color.BLACK.rgb)
                } else {
                    pixelImage.setRGB(x, y, Color.WHITE.rgb)
                }
            }
        }
        image.print {
            when (it) {
                COLOR_WHITE -> " . "
                COLOR_BLACK -> " # "
                else -> "."
            }
        }

        val imageFile = File("TEST_IMAGE.png")
        ImageIO.write(pixelImage, "png", imageFile)
        return "CYUAH"
    }

    companion object {
        const val WIDTH = 25
        const val HEIGHT = 6
        const val WIDTH_SCALE_FACTOR = 10
        const val HEIGHT_SCALE_FACTOR = 10
        const val COLOR_WHITE = 0
        const val COLOR_BLACK = 1
        const val COLOR_TRANSPARENT = 2
    }

}

fun main() = runBlocking {
    PuzzleRunner(listOf(Y2019D08())).run()

}



