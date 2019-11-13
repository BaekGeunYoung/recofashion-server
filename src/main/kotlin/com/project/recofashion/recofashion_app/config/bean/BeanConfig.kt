package com.project.recofashion.recofashion_app.config.bean

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class BeanConfig {
    @Bean
    fun bCryptPasswordEncoder() : BCryptPasswordEncoder = BCryptPasswordEncoder()
}