package com.portfolio.authentication.service

import com.portfolio.authentication.model.user.User
import com.portfolio.authentication.model.user.UserInsert
import com.portfolio.authentication.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {
    fun createUser(userInsert: UserInsert): User {
        val user = User.create(userInsert)
        return userRepository.save(user)
    }

    fun getUser(username: String): User? {
        return userRepository.findByUsername(username)
    }
}