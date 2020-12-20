package com.kotlin.practice.spa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpaApplication

fun main(args: Array<String>) {
    runApplication<SpaApplication>(*args)
}
