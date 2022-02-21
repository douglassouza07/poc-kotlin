package br.com.poc.exception

import br.com.poc.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBusinessException(
        exception: BusinessException,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = mapBindingResult(exception.bindingResult),
            path = request.servletPath
        )
    }

    fun mapBindingResult(bindingResult: BindingResult): String? {
        var msg: String? = "";
        bindingResult.allErrors.forEach {
            msg += it.defaultMessage;
        }
        val errors = bindingResult.allErrors.map { msg = it.defaultMessage }.filterNotNull();
        return msg;
    }
}