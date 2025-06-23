package com.motycka.edu.lesson02

const val espresso = "Espresso"
const val cappuccino = "Cappuccino"

const val espressoPrice = 2.5
const val cappuccinoPrice = 3.0

fun variables(espressoQuantity: Int, cappuccinoQuantity: Int) {
    val totalPrice: Double = (espressoPrice * espressoQuantity) + (cappuccinoPrice * cappuccinoQuantity)
    println(totalPrice)
}

fun main(args: Array<String>) {
    val espressoQuantity = args.count { it == espresso }
    val cappuccinoQuantity = args.count { it == cappuccino }

    variables(espressoQuantity = espressoQuantity, cappuccinoQuantity = cappuccinoQuantity)
}
