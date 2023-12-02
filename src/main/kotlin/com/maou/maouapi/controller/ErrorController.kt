package com.maou.maouapi.controller

import com.maou.maouapi.exception.NotFoundException
import com.maou.maouapi.exception.UnauthorizedException
import com.maou.maouapi.model.ApiResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        value = [ConstraintViolationException::class]
    )
    fun validationHandler(constraintViolationException: ConstraintViolationException): ApiResponse<String> =
        ApiResponse(
            code = 400,
            status = "Bad request",
            data = constraintViolationException.message.toString()
        )


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(notFoundException: NotFoundException): ApiResponse<String> =
        ApiResponse(
            code = 404,
            status = "Not found",
            data = notFoundException.message.toString()
        )

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorizedHandler(unauthorizedException: UnauthorizedException): ApiResponse<String> =
        ApiResponse(
            code = 401,
            status = "Unauthorized",
            data = unauthorizedException.message.toString()
        )

}