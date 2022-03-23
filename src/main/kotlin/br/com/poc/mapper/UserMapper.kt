package br.com.poc.mapper

import br.com.poc.dto.UserDto
import br.com.poc.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun map(dto: UserDto): User {
        return User(
            name = dto.name,
            document = dto.document,
            birth = dto.birth,
            address = dto.address
        )
    }
}