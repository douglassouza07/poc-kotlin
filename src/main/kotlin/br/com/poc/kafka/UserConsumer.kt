package br.com.poc.kafka

import br.com.poc.util.StatusUser
import br.com.poc.model.User
import br.com.poc.service.UserService
import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UserConsumer(
    private val userService: UserService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["user-topic"], groupId = "topic_cadastro-consumer")
    fun processMessage(playload: String) {
        var user = Gson().fromJson(playload, User::class.java)
        log.info("Consumindo dados {}")
        user.status = StatusUser.ATIVO
        userService.update(user);
        log.info("User atualizado com sucesso {}")
    }
}