package br.com.poc.controller

import br.com.poc.model.User
import br.com.poc.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class Controller (
    private val userService: UserService
)
{
    @PostMapping
    fun create(@Valid @RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}