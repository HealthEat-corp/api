package com.healtheat.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class HealtheatApiApplication{

}

fun main(args: Array<String>) {
    runApplication<HealtheatApiApplication>(*args)
}
