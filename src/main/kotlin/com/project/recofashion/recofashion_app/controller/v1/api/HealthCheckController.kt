package com.project.recofashion.recofashion_app.controller.v1.api

import org.springframework.web.bind.annotation.*

@RestController
class HealthCheckController
{
    @GetMapping("/")
    fun healthCheck() : Map<String, String> {
        val ret: MutableMap<String, String> = HashMap()
        ret["message"] = "ok"

        return ret
    }
}