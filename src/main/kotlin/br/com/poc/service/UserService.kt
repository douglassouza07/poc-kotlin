package br.com.poc.service

import br.com.poc.dto.StatusUser
import br.com.poc.exception.BusinessException
import br.com.poc.kafka.KafkaProducer
import br.com.poc.model.User
import br.com.poc.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val kafkaProducer: KafkaProducer

) {
    fun save(user: User): User {
        user.documento?.let {
            val vo: User? = userRepository.findByDocumento(user.documento);
            if (Objects.nonNull(vo)) {
                throw BusinessException("O cpf/cnpj ja esta cadastrado");
            }
        }
        user.status = StatusUser.PRECADASTRO
        userRepository.save(user);
        kafkaProducer.send(user)
        return user;
    }
}