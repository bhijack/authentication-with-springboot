package com.portfolio.authentication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
class AuthenticationApplication

fun main(args: Array<String>) {
	runApplication<AuthenticationApplication>(*args)
}
