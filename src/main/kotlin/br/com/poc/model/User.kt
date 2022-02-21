package br.com.poc.model

import br.com.poc.dto.StatusUser
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name = "Usuario")
data class User(
    @Id @GeneratedValue
    val id: Long? = null,

    @field:NotEmpty(message = "O documento é obrigatorio")
    val documento: String? = null,

    @field:Size(min= 5, message = "O nome não pode ter menos de 5 caracetere")
    val nome: String? = null,

    var status: StatusUser? = null,

    @field:Size(min= 10,message = "O Endereco  é obrigatorio")
    val endereco: String? = null,

    @field:NotNull(message = "A data de nascsimento é obrigatorio")
    val dtNascimento: LocalDate? = null

)