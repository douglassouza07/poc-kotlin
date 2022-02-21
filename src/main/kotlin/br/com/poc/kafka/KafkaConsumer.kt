package br.com.poc.kafka

import br.com.poc.dto.StatusUser
import br.com.poc.model.User
import br.com.poc.service.UserService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    private val userService: UserService
) {

    @KafkaListener(topics = ["topic_cadastro"], groupId = "topic_cadastro-consumer")
    fun processMessage(user: User) {
        user.status = StatusUser.ATIVO
        userService.save(user);
    }
}