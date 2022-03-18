package br.com.poc.util

import org.springframework.stereotype.Component

@Component
class ValidateDocument {

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