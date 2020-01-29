package com.project.recofashion.recofashion_app.config.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    @Value("\${DEPLOY_HOST}")
    private val deployHost: String = ""

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000", deployHost)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("authorization", "content-type")
                .exposedHeaders("authorization")
                .maxAge(3600)
    }
}