package com.portfolio.authentication.configuration

import com.portfolio.authentication.service.AuthorizeUserDetailService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
        val userDetailsService: AuthorizeUserDetailService
        ): WebSecurityConfigurerAdapter() {

    @Value("\${server-to-server.username}")
    val username: String? = null

    @Value("\${server-to-server.password}")
    val password: String? = null

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http
                .authorizeRequests()
                .antMatchers("/actuator/health").permitAll()
                .anyRequest().hasAnyRole("SUPERUSER")
                .and().httpBasic()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.inMemoryAuthentication().withUser(username).password(passwordEncoder().encode(password)).roles("SUPERUSER")
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .authenticationProvider(authenticationProvider())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

}