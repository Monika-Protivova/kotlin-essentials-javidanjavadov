package com.motycka.edu.lesson02

val coffeeOrders = mutableMapOf<Int, List<String>>()
var nextOrderId = 1

fun processOrder(items: List<String>, payment: Double): Double {
    val orderId = placerOrder(items)
    val totalToPay = payOrder(orderId)

    if (payment < totalToPay) {
        throw IllegalArgumentException("Insufficient payment. Required: $totalToPay, provided: $payment")
    }

    val change = payment - totalToPay
    println("Payment successful for Order ID $orderId. Change: $change")
    completeOrder(orderId)

    return change
}

fun placerOrder(items: List<String>): Int {
    val orderId = nextOrderId++
    coffeeOrders[orderId] = items
    return orderId
}

fun payOrder(orderId: Int): Double {
    val items = coffeeOrders[orderId] ?: throw IllegalArgumentException("Order ID $orderId does not exist")
    val prices = items.map { getPrice(it) }
    val total = prices.sum()
    val discount = if (items.size >= 3) prices.minOrNull() ?: 0.0 else 0.0
    return total - discount
}

fun completeOrder(orderId: Int) {
    if (!coffeeOrders.containsKey(orderId)) throw IllegalArgumentException("Order ID $orderId does not exist")
    coffeeOrders.remove(orderId)
}

fun getPrice(item: String): Double = when (item) {
    ESPRESSO -> ESPRESSO_PRICE
    DOUBLE_ESPRESSO -> DOUBLE_ESPRESSO_PRICE
    CAPPUCCINO -> CAPPUCCINO_PRICE
    LATTE -> LATTE_PRICE
    AMERICANO -> AMERICANO_PRICE
    FLAT_WHITE -> FLAT_WHITE_PRICE
    else -> throw IllegalArgumentException("$item is not on the menu")
}

fun main() {
    processOrder(listOf(ESPRESSO, CAPPUCCINO, CAPPUCCINO, AMERICANO), 20.0)
    processOrder(listOf(ESPRESSO, FLAT_WHITE, AMERICANO), 10.0)
    // processOrder(listOf(ESPRESSO, ESPRESSO, DOUBLE_ESPRESSO), 5.0)
}
