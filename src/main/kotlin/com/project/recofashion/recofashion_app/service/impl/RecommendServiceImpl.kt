package com.project.recofashion.recofashion_app.service.impl

import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.entity.user.User
import com.project.recofashion.recofashion_app.repository.HistoryRepository
import com.project.recofashion.recofashion_app.repository.UserRepository
import com.project.recofashion.recofashion_app.service.RecommendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class RecommendServiceImpl(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val historyRepository: HistoryRepository
) : RecommendService {
    private val SHORT = "short"
    private val SHORT_SLEEVE = "short sleeve"
    private val SLEEVELESS = "shortless"
    private val COTTON_PANTS = "cotton pants"
    private val JEANS = "jeans"
    private val CARDIGAN = "cardigan"
    private val LONG_SLEEVE = "long sleeve"
    private val KNIT = "knit"
    private val JACKET = "jacket"
    private val JUMPER = "jumper"
    private val TRENCH_COAT = "trench coat"
    private val COAT = "coat"
    private val LEATHER_JACKET = "leather jacket"
    private val HITTEK = "hittek"
    private val PADDING = "padding"
    private val SCARF = "scarf"
    private val FLEECE_LINED = "fleece-lined"

    override fun recommend(temperature: Int, userDetails: UserDetails): MutableMap<String, Any> {
        val ret: MutableMap<String, Any> = HashMap()
        val user = userRepository.findByUsername(userDetails.username)

        user?: throw UsernameNotFoundException("cannot find such user : ${userDetails.username}")

        ret["clothes"] = recommendClothes(temperature)
        ret["colors"] = recommendColors(temperature, user)
    }
    
    fun recommendClothes(temperature: Int) : List<String> {
        return listOf("jacket", "padding", "coat")
    }

    fun recommendColors(temperature: Int, user: User) : List<Color> = listOf(Color(255, 255, 0))
}