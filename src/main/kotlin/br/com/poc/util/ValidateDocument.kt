package br.com.poc.util

import br.com.poc.exception.BusinessException
import br.com.poc.model.User
import org.springframework.stereotype.Component

@Component
class ValidateDocument {
    fun cpfCnpj(user: User): User {
        when (user.document?.length) {
            11 -> {
                if (checkCpf(user.document)) user.documentType = DocumentType.CPF
                else throw BusinessException("CPF inválido")
            }
            14 -> {
                if (checkCnpj(user.document)) user.documentType = DocumentType.CNPJ
                else throw BusinessException("CNPJ inválido")
            }
            else -> {
                throw BusinessException("Não é um CPF nem CNPJ válido.")
            }
        }
        return user;
    }

    fun checkCpf(document: String): Boolean {
        var calc: Int
        var num = 10
        var sum = 0
        for (x in 0..8) {
            calc = document[x].toString().toInt() * num
            sum += calc
            --num
        }
        var rest = sum % 11
        var test = 11 - rest
        if (test > 9) test = 0
        if (test != document[9].toString().toInt()) return false
        num = 11
        sum = 0
        for (x in 0..9) {
            calc = document[x].toString().toInt() * num
            sum += calc
            --num
        }
        rest = sum % 11
        test = 11 - rest
        if (test > 9) test = 0
        if (test != document[10].toString().toInt()) return false
        return true
    }

    fun checkCnpj(document: String): Boolean {
        var calc: Int
        var num = 5
        var sum = 0
        for (x in 0..11) {
            calc = document[x].toString().toInt() * num
            sum += calc
            --num
            if (num == 1) num = 9
        }
        var rest = sum % 11
        var test = 11 - rest
        if (test < 2) test = 0
        if (test != document[12].toString().toInt()) return false
        num = 6
        sum = 0
        for (x in 0..12) {
            calc = document[x].toString().toInt() * num
            sum += calc
            --num
            if (num == 1) num = 9
        }
        rest = sum % 11
        test = 11 - rest
        if (test < 2) test = 0
        if (test != document[13].toString().toInt()) return false
        return true
    }
}