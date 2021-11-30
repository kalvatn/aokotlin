package com.kalvatn.aoc.common.geometry

@Suppress("unused")
fun Vec3.toMutableVec3() = MutableVec3(this.x, this.y, this.z)

operator fun Vec3.plus(other: Vec3) = Vec3(this.x + other.x, this.y + other.y, this.z + other.z)

fun Vec3.fromComparison(other: Vec3) = Vec3(
  this.x.compareTo(other.x),
  this.y.compareTo(other.y),
  this.z.compareTo(other.z)
)
