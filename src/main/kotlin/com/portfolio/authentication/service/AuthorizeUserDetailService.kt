package com.portfolio.authentication.service

import com.portfolio.authentication.exception.ErrorCodeException
import com.portfolio.authentication.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizeUserDetailService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
                ?: throw ErrorCodeException("User not found", HttpStatus.NOT_FOUND)
        return AuthorizedUser(user, getAuthority(user))
    }

    fun getAuthority(user: com.portfolio.authentication.model.user.User): List<GrantedAuthority> {
        val role = user.role
        val authorityList = mutableListOf<GrantedAuthority>()
        authorityList.add(SimpleGrantedAuthority("ROLE_${role?.toUpperCase()}"))
        return authorityList
    }
}

private class AuthorizedUser : User {
    constructor(user: com.portfolio.authentication.model.user.User,
                authorities: List<GrantedAuthority> = listOf(),
                enable: Boolean = true,
                accountNotExpire: Boolean = true,
                credentialNotExpire: Boolean = true,
                accountNonLock: Boolean = true) :
            super(user.username, user.password, enable, accountNotExpire, credentialNotExpire, accountNonLock, authorities)

}