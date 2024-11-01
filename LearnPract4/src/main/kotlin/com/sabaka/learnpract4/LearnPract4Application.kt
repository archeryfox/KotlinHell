package com.sabaka.learnpract4

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sabaka.learnpract4"])
class LearnPract4Application

fun main(args: Array<String>) {
	runApplication<LearnPract4Application>(*args)
}