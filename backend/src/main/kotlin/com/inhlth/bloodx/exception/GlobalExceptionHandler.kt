package com.inhlth.bloodx.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401 Unauthorized
    fun handleUnauthorizedException(ex: UnauthorizedException): String {
        return ex.message ?: "Unauthorized"
    }
}