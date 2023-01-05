package xyz.jamesnuge.fantasy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FantasyApplication

fun main(args: Array<String>) {
	runApplication<FantasyApplication>(*args)
}
