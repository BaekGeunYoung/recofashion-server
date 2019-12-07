package com.project.recofashion.recofashion_app.config.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000", "http://52.79.179.196")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("authorization", "content-type")
                .exposedHeaders("authorization")
                .maxAge(3600)
    }
}