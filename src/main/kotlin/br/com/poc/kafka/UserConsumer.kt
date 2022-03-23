package br.com.poc.kafka

import br.com.poc.model.User
import br.com.poc.service.UserService
import br.com.poc.util.StatusUser
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
class UserConsumer(
    private val userService: UserService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["user-topic"], groupId = "topic_cadastro-consumer")
    fun processMessage(user: User) {
        log.info("Recebendo mensagem user-topic {}", user)
        user.status = StatusUser.ATIVO
        userService.update(user);
        log.info("User atualizado com sucesso {}")
    }
}