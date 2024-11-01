package com.sabaka.journal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.sabaka.journal.repository"])
@EntityScan(basePackages = ["com.sabaka.journal.model"]) // Add this line
class JournalApplication

fun main(args: Array<String>) {
    runApplication<JournalApplication>(*args)
}
