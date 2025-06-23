package com.motycka.edu.lesson03

import kotlin.math.PI

object AreaCalculator {
    fun calculateRectangleArea(a: Double, b: Double): Double = a * b
    fun calculateRectangleArea(a: Int, b: Int): Double = a.toDouble() * b.toDouble()
    fun calculateCircleArea(r: Double): Double = PI * r * r
    fun calculateCircleArea(r: Int): Double = PI * r * r
    fun calculateTriangleArea(a: Double, b: Double): Double = (a * b) / 2
    fun calculateTriangleArea(a: Int, b: Int): Double = (a.toDouble() * b.toDouble()) / 2
}

fun main() {
    println(AreaCalculator.calculateRectangleArea(5, 10))
    println(AreaCalculator.calculateRectangleArea(5.0, 10.0))
    println(AreaCalculator.calculateCircleArea(3))
    println(AreaCalculator.calculateCircleArea(3.0))
    println(AreaCalculator.calculateTriangleArea(6, 4))
    println(AreaCalculator.calculateTriangleArea(6.0, 4.0))
}
