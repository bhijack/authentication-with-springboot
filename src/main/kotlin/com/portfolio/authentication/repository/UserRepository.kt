package com.portfolio.authentication.repository

import com.portfolio.authentication.model.user.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository: MongoRepository<User, String> {
    @Query("{'username' : ?0}")
    fun findByUsername(username: String): User?
}