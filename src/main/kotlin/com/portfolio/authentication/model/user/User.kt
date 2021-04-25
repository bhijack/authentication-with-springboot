package com.portfolio.authentication.model.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Document(collection = "user")
@CompoundIndexes(
        CompoundIndex(name = "user_unique", def = "{'username' : 1}", unique = true)
)
class User(
        @Id
        var id: String? = null,
        @field:Field("username")
        var username: String? = null,
        @field:Field("password")
        var password: String? = null,
        @field:Field("role")
        var role: String = "ADMIN"
) {
        companion object {
                fun create(userInsert: UserInsert): User {
                        return User(
                                username = userInsert.username,
                                password = BCryptPasswordEncoder().encode(userInsert.password),
                                role = userInsert.role ?: "ADMIN"
                        )
                }
        }
}