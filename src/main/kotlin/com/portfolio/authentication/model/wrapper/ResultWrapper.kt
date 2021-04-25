package com.portfolio.authentication.model.wrapper

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ResultWrapper<T> {
    var status: Int? = null
    var message: String? = null
    var data: T? = null

    constructor(status: Int?, message: String) {
        this.status = status
        this.message = message
    }

    constructor(status: Int?, message: String, data: T) {
        this.status = status
        this.message = message
        this.data = data
    }
}
