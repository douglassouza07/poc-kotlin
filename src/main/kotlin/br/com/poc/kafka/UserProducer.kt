package br.com.poc.kafka

import br.com.poc.model.User
import com.google.gson.Gson
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Slf4j
@Component
class UserProducer(
    @Value("\${topic-user}") val topic: String,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun send(user: User) {
        try {
            log.info("Enviando mensagem para o Kafka {}", user)
            var playload = Gson().toJson(user);
            kafkaTemplate.send(topic,playload)
            log.info("Mensagem eviada com sucesso")
        } catch (e: Exception) {
            throw  e;
        }
    }
}





