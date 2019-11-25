package com.project.recofashion.recofashion_app.config.controller

import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@ControllerAdvice
class NoContentControllerAdvice : ResponseBodyAdvice<Void> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        if(returnType.parameterType.isAssignableFrom(Unit::class.java)) return true
        return false
    }

    override fun beforeBodyWrite(body: Void?, returnType: MethodParameter, selectedContentType: MediaType, selectedConverterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): Void? {
        if(returnType.parameterType.isAssignableFrom(Unit::class.java)) {
            response.setStatusCode(HttpStatus.NO_CONTENT)
        }

        return body
    }

}