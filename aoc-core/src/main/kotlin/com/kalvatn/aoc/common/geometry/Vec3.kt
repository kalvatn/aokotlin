package com.kalvatn.aoc.common.geometry

data class Vec3(
  val x: Int,
  val y: Int,
  val z: Int
)

@Suppress("unused")
fun Vec3.toMutableVec3() = MutableVec3(this.x, this.y, this.z)

data class MutableVec3(
  var x: Int,
  var y: Int,
  var z: Int
)

operator fun Vec3.plus(other: Vec3) = Vec3(this.x + other.x, this.y + other.y, this.z + other.z)

fun Vec3.fromComparison(other: Vec3) = Vec3(
  this.x.compareTo(other.x),
  this.y.compareTo(other.y),
  this.z.compareTo(other.z)
)
