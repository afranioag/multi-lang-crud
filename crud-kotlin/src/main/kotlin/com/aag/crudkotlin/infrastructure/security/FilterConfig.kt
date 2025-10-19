package com.aag.crudkotlin.infrastructure.config

import com.aag.crudkotlin.infrastructure.security.JwtFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun jwtFilterRegistration(jwtFilter: JwtFilter): FilterRegistrationBean<JwtFilter> {
        val registrationBean = FilterRegistrationBean<JwtFilter>()
        registrationBean.filter = jwtFilter
        registrationBean.addUrlPatterns("/v1/*")
        registrationBean.order = 1
        return registrationBean
    }
}