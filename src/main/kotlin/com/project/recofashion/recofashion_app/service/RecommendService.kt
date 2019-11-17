package com.project.recofashion.recofashion_app.service

import org.springframework.security.core.userdetails.UserDetails

interface RecommendService {
    fun recommend(temperature: Int, tone: String, userDetails: UserDetails): MutableMap<String, Any>
}