package com.portfolio.authentication.controller

import com.portfolio.authentication.model.wrapper.RestResultWrapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    @RequestMapping(path= [""],method = [RequestMethod.GET])
    fun checkup(): RestResultWrapper<String> {
        return RestResultWrapper("Server running..",HttpStatus.OK)
    }

    @RequestMapping(path= ["/oauth-superuser"],method = [RequestMethod.GET])
    fun checkupSuperuser(): RestResultWrapper<String> {
        return RestResultWrapper("[SUPERUSER] Server running..",HttpStatus.OK)
    }

    @RequestMapping(path= ["/basic-only"],method = [RequestMethod.GET])
    fun checkupOnlyBasic(): RestResultWrapper<String> {
        return RestResultWrapper("Server running..",HttpStatus.OK)
    }
}