package br.com.poc.controller

import br.com.poc.dto.UserDto
import br.com.poc.mapper.UserMapper
import br.com.poc.model.User
import br.com.poc.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService,
    private val mapper: UserMapper
)
{
    @PostMapping
    fun create(@Valid @RequestBody userDto: UserDto): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(mapper.map(userDto)));
    }
    @GetMapping
    fun list(): ResponseEntity<List<User>> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.list());
    }
}