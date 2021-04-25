package com.portfolio.authentication.event

import com.portfolio.authentication.model.user.UserInsert
import com.portfolio.authentication.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class OnStartUpApplicationConfiguration: ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private lateinit var userService: UserService

    override fun onApplicationEvent(p0: ApplicationReadyEvent) {
        val admin = userService.getUser("admin")
        if (admin == null) {
            userService.createUser(
                    UserInsert(
                            username = "admin",
                            password = "admin",
                            role = "SUPERUSER"
                    )
            )
        }
    }
}