package com.portfolio.authentication.controller

import com.portfolio.authentication.model.user.User
import com.portfolio.authentication.model.user.UserInsert
import com.portfolio.authentication.model.wrapper.RestResultWrapper
import com.portfolio.authentication.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
        val userService: UserService
) {
    @RequestMapping(path = [""], method = [RequestMethod.POST])
    fun createUser(
            @RequestBody userInsert: UserInsert
    ): RestResultWrapper<User> {
        return RestResultWrapper(userService.createUser(userInsert),HttpStatus.OK)
    }
}