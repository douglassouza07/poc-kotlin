package br.com.poc.kafka

import br.com.poc.model.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class UserProducer(
    @Value("\${topic-user}") val topic: String,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun send(user: User) {
        try {
            log.info("Enviando mensagem para o Kafka {}", user)
            kafkaTemplate.send(topic,user)
            log.info("Mensagem eviada com sucesso")
        } catch (e: Exception) {
            throw  e;
        }
    }
}





