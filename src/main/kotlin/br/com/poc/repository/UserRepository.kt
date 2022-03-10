package br.com.poc.repository

import br.com.poc.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
     fun findByDocument(cpfCnpj: String?): User?
}