package br.com.poc.model

import br.com.poc.util.StatusUser
import br.com.poc.util.DocumentType
import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity(name = "User")
data class User(
    @Id
    @GeneratedValue
    val id: Long = 0,

    val document: String? = null,

    val name: String? = null,

    var status: StatusUser = StatusUser.PRECADASTRO,

    val address: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    val birth: Date? = null,

    var documentType: DocumentType = DocumentType.CPF
)