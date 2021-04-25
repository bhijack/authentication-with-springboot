package com.portfolio.authentication.configuration

import com.portfolio.authentication.service.AuthorizeUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer

@Configuration
@EnableAuthorizationServer
class OAuth2AuthenServer(
        @Autowired @Qualifier("authenticationManagerBean") val authenticationManager: AuthenticationManager,
        val passwordEncoder: PasswordEncoder
        ): AuthorizationServerConfigurerAdapter() {

    @Value("\${spring.oauth2.client-id}")
    val clientId: String? = null

    @Value("\${spring.oauth2.secret}")
    val secret: String? = null


    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
//                .tokenStore(this.redisTokenStore)
                .authenticationManager(authenticationManager)
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(secret))
                .authorizedGrantTypes("authorization_code","password","refresh_token")
                .scopes("read","write","openid")
                .accessTokenValiditySeconds(3000000)
    }
}