package com.project.recofashion.recofashion_app.service

import com.project.recofashion.recofashion_app.entity.user.Color
import org.springframework.security.core.userdetails.UserDetails

interface RecommendService {
    fun recommend(temperature: Int, tone: String, userDetails: UserDetails): MutableMap<String, Any>

    fun search(color: Color, temperature: Int): MutableMap<String, Any>
}