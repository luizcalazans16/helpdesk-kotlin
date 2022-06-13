package com.unilasalle.helpdesk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class HelpdeskLasalleDoresApplication

fun main(args: Array<String>) {
	runApplication<HelpdeskLasalleDoresApplication>(*args)
}
