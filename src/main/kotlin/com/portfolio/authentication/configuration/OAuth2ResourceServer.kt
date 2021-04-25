package com.portfolio.authentication.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest

@Configuration
@EnableResourceServer
class OAuth2ResourceServer: ResourceServerConfigurerAdapter() {
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId("authserver")
    }
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http
                .requestMatcher(OAuthRequestedMatcher())
                .authorizeRequests()
                .antMatchers("/test","/test/").authenticated()
                .antMatchers("/test/**").hasAnyRole("SUPERUSER")
                .anyRequest().denyAll()
    }
    private class OAuthRequestedMatcher : RequestMatcher {
        override fun matches(request: HttpServletRequest): Boolean {
            val auth = request.getHeader("Authorization")
            return auth != null && (auth.toLowerCase().startsWith("bearer"))
        }
    }
}