package br.com.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
class PocApplication

fun main(args: Array<String>) {
	runApplication<PocApplication>(*args)
}
