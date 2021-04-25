package com.portfolio.authentication.exception

import org.springframework.http.HttpStatus

class ErrorCodeException(val exceptionMessage: String, val status: HttpStatus) : Exception(exceptionMessage)