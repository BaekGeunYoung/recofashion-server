package com.project.recofashion.recofashion_app.service.impl

import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.repository.HistoryRepository
import com.project.recofashion.recofashion_app.repository.UserRepository
import com.project.recofashion.recofashion_app.service.RecommendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class RecommendServiceImpl(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val historyRepository: HistoryRepository
) : RecommendService {
    override fun recommend(temperature: Int, userDetails: UserDetails): MutableMap<String, Any> {
        val user = userRepository.findByUsername(userDetails.username)

    }
    
    fun recommendClothes(temperature: Int) : List<String> {
        return listOf("jacket", "padding", "coat")
    }

    fun recommendColors() : List<Color> = listOf(Color(255, 255, 0))
}