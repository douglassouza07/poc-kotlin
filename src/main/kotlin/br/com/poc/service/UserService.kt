package br.com.poc.service

import br.com.poc.exception.BusinessException
import br.com.poc.kafka.UserProducer
import br.com.poc.model.User
import br.com.poc.repository.UserRepository
import br.com.poc.util.DocumentType
import br.com.poc.util.StatusUser
import br.com.poc.util.ValidateDocument
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository,
    private val userProducer: UserProducer,
    private val validateDocument: ValidateDocument
) {
    fun save(user: User): User {
        validate(user);
        user.status = StatusUser.PRECADASTRO
        userRepository.save(user);
        userProducer.send(user)
        return user;
    }

    fun update(user: User): User {
        userRepository.save(user);
        return user;
    }

    fun list(): List<User> {
        return userRepository.findAll();
    }

    fun validate(user: User) {
        userRepository.findByDocument(user.document)?.let {
            throw BusinessException("O cpf/cnpj ja esta cadastrado")
        }
        when (user.document?.length) {
            11 -> {
                if (validateDocument.checkCpf(user.document))
                    user.documentType = DocumentType.CPF
                else throw BusinessException("CPF inválido")
            }
            14 -> {
                if (validateDocument.checkCnpj(user.document))
                    user.documentType = DocumentType.CNPJ
                else throw BusinessException("CNPJ inválido")
            }
            else -> {
                throw BusinessException("Não é um CPF nem CNPJ válido.")
            }
        }
    }
}