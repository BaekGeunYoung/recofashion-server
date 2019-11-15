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
    private val SHORT = "short"
    private val SHORT_SLEEVE = "short sleeve"
    private val SLEEVELESS = "short sleeve"
    private val COTTON_PANTS = "short sleeve"
    private val JEANS = "short sleeve"
    private val CARDIGAN = "short sleeve"
    private val LONG_SLEEVE = "short sleeve"
    private val KNIT = "short sleeve"
    private val JACKET = "short sleeve"
    private val JUMPER = "short sleeve"
    private val TRENCH_COAT = "trench coat"
    private val COAT = "coat"
    private val LEATHER_JACKET = "leather jacket"
    private val HITTEK = "hittek"
    private val PADDIGN = "padding"
    private val SCARF = "scarf"
    private val FLEECE_LINED = "fleece-lined"

    override fun recommend(temperature: Int, userDetails: UserDetails): MutableMap<String, Any> {
        val user = userRepository.findByUsername(userDetails.username)

    }
    
    fun recommendClothes(temperature: Int) : List<String> {
        return listOf("jacket", "padding", "coat")
    }

    fun recommendColors() : List<Color> = listOf(Color(255, 255, 0))
}