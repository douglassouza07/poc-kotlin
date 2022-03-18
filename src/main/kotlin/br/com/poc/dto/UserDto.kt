package br.com.poc.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent
import javax.validation.constraints.Size

data class UserDto(
    @get:NotEmpty(message = "O documento é obrigatorio")
    val document: String? = null,

    @get:NotEmpty(message = "O nome é obrigatorio")
    @get:Size(min = 5, message = "O nome não pode ter menos de 5 caracteres")
    val name: String? = null,

    @get:NotEmpty(message = "O Endereco é obrigatorio")
    @get:Size(min = 10, message = "O Endereco não pode ter menos de 10 caracteres")
    val address: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @get:NotNull(message = "A data de nascimento é obrigatorio")
    @get:PastOrPresent
    val birth: Date? = null
)
