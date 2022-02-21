package br.com.poc.kafka

import br.com.poc.model.User
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, User>) {

    fun send(user: User) {
        kafkaTemplate.send("topic_cadastro", user)
    }

}