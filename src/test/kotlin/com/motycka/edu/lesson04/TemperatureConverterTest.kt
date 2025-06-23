package com.motycka.edu.lesson04

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class TemperatureConverterTest : StringSpec({
    "convert Fahrenheit 32 to Celsius should be 0" {
        val fahrenheit = 32.0
        val celsius = TemperatureConverter.toCelsius(fahrenheit)
        celsius shouldBe 0.0
    }

    "convert Fahrenheit 212 to Celsius should be 100" {
        val fahrenheit = 212.0
        val celsius = TemperatureConverter.toCelsius(fahrenheit)
        celsius shouldBe 100.0
    }

    "convert Fahrenheit 98.6 to Celsius should be approx 37" {
        val fahrenheit = 98.6
        val celsius = TemperatureConverter.toCelsius(fahrenheit)
        celsius shouldBe (37.0 plusOrMinus 0.1)
    }

    "convert negative Fahrenheit to Celsius" {
        val fahrenheit = -40.0
        val celsius = TemperatureConverter.toCelsius(fahrenheit)
        celsius shouldBe -40.0
    }
})
