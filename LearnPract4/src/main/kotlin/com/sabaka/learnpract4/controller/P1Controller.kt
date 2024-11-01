package com.sabaka.learnpract4.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class P1Controller {

    @GetMapping("/")
    fun index(): String {
        return "Home"
    }

    @GetMapping("/calculator")
    fun calculator(): String {
        return "calculator"
    }

    @PostMapping("/calculate")
    fun calculate(
        @RequestParam num1: Double,
        @RequestParam num2: Double,
        @RequestParam operation: String,
        model: Model
    ): String {
        val result = when (operation) {
            "add" -> num1 + num2
            "subtract" -> num1 - num2
            "multiply" -> num1 * num2
            "divide" -> num1 / num2
            else -> 0.0
        }
        model.asMap().put("result", result)
        return "result"
    }

    @GetMapping("/converter")
    fun converter(): String {
        return "converter"
    }


    @PostMapping("/convert")
    fun convert(
        @RequestParam fromCurrency: String,
        @RequestParam toCurrency: String,
        @RequestParam amount: Double,
        model: Model
    ): String {
        var convertedAmount: Double = 0.0
        if (fromCurrency == toCurrency) {
            convertedAmount = amount
        } else {
            when (Any()) {
                (fromCurrency == "RUB") -> convertedAmount = amount * 0
                (fromCurrency == "USD" && toCurrency == "EUR") -> convertedAmount = amount * 0.92
                (fromCurrency == "EUR" && toCurrency == "USD") -> convertedAmount = amount * 1.09

            }
        }
        if ((fromCurrency == "USD" || fromCurrency == "EUR") && (toCurrency == "UDS" || toCurrency == "EUR")) {
            model.asMap().put("convertedAmount", convertedAmount)
            model.asMap().put("currency", toCurrency)
        } else if(fromCurrency == "RUB" || toCurrency == "RUB") {
            model.asMap().put("convertedAmount", "копеки")
            model.asMap().put("currency", ":(")
        }
        return "conversionResult"
    }
}
