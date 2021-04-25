package com.portfolio.authentication.model.wrapper

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.portfolio.authentication.exception.ErrorCodeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class RestResultWrapper<T> : ResponseEntity<ResultWrapper<T>> {
    constructor(status: HttpStatus) : super(status) {}

    constructor(ex: ErrorCodeException) : super(ResultWrapper<T>(ex.status.value(), ex.message
            ?: "Unknown error"), ex.status) {}

    constructor(body: T, status: HttpStatus) : super(ResultWrapper(status.value(), status.name, body), status) {}

    constructor(headers: MultiValueMap<String, String>, status: HttpStatus) : super(headers, status) {}

    constructor(body: T, headers: MultiValueMap<String, String>, status: HttpStatus) : super(ResultWrapper(status.value(), status.name, body), headers, status) {}
}
