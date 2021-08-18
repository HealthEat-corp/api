package com.healtheat.api.exception

import com.healtheat.api.RestApiBaseFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessErrorException::class)
    fun businessErrorException(businessErrorException: BusinessErrorException): ResponseEntity<RestApiBaseFormat>{
        return ResponseEntity.badRequest().body(RestApiBaseFormat(message = businessErrorException.message))
    }
}
