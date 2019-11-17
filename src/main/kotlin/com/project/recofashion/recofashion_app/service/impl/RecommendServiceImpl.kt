package com.project.recofashion.recofashion_app.service.impl

import com.project.recofashion.recofashion_app.entity.history.History
import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.entity.user.User
import com.project.recofashion.recofashion_app.main
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
    private val SLEEVELESS = "sleeveless"
    private val SLACKS = "slacks"
    private val SHIRTS = "shirts"
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
    private val HOOD = "hood"
    private val MAN_TO_MAN = "man to man"
    private val mainColors = listOf("RED", "ORANGE", "BLUE", "GREEN", "PURPLE")
    private val monoColors = listOf("GRAY", "WHITE", "BLACK")

    override fun recommend(temperature: Int, userDetails: UserDetails): MutableMap<String, Any> {
        val ret: MutableMap<String, Any> = HashMap()
        val user = userRepository.findByUsername(userDetails.username)

        user?: throw UsernameNotFoundException("cannot find such user : ${userDetails.username}")

        ret["clothes"] = recommendClothes(temperature)
        ret["mainColor"] = recommendMainColor(temperature, user)

        return ret
    }
    
    fun recommendClothes(temperature: Int) : List<String> {
        if(temperature >= 27) return listOf(SHORT, SHORT_SLEEVE, SLEEVELESS)
        if(temperature >= 23) return listOf(SHORT, SHORT_SLEEVE, COTTON_PANTS)
        if(temperature >= 20) return listOf(LONG_SLEEVE, CARDIGAN, HOOD, COTTON_PANTS, SLACKS)
        if(temperature >= 17) return listOf(SHIRTS, MAN_TO_MAN, CARDIGAN, HOOD, JEANS, COTTON_PANTS, SLACKS)
        if(temperature >= 12) return listOf(KNIT, SHIRTS, MAN_TO_MAN, CARDIGAN, HOOD, JACKET, JEANS)
        if(temperature >= 10) return listOf(KNIT, JUMPER, MAN_TO_MAN, HOOD, LEATHER_JACKET, TRENCH_COAT)
        if(temperature >= 6) return listOf(COAT, LEATHER_JACKET, JUMPER, HITTEK)
        return listOf(HITTEK, COAT, PADDING, SCARF)
    }

    fun recommendMainColor(temperature: Int, user: User) : String {
        val previous: List<History>? = historyRepository.findByUsername(user.username)
        return getMainColor(previous!!, user)
    }

    fun getMainColor(previous: List<History>, user: User): String {
        val score: MutableMap<String, Int> = HashMap()

        for(x in mainColors) score[x] = 100
        for(x in monoColors) score[x] = 100

        scoreByPreviousData(previous, score)
        scoreByFavorite(user, score)

        val best = score.maxBy { it.value }

        return best!!.key
    }

    fun scoreByPreviousData(previous: List<History>, score: MutableMap<String, Int>) {
        /*사용자의 과거 데이터를 가져와 최신 순으로 정렬*/
        val sortedPrevious = previous.sortedByDescending { it.date }

        val max = if(sortedPrevious.size >= 3) 3 else sortedPrevious.size

        for(i in 0 until max) {
            val targetHist = sortedPrevious[i]

            val pantsColor = targetHist.pantsColor.mainColor()
            val topColor = targetHist.topColor.mainColor()

            if(pantsColor in mainColors || pantsColor in monoColors)
                score[pantsColor] = score[pantsColor]!!.minus((30 - i * 10))
            if(topColor in mainColors || topColor in monoColors)
                score[topColor] = score[topColor]!!.minus((30 - i * 10))
        }
    }

    fun scoreByFavorite(user: User, score: MutableMap<String, Int>) {
        for(x in user.favoriteColors) {
            if(x.mainColor() in mainColors || x.mainColor() in monoColors)
                score[x.mainColor()] = score[x.mainColor()]!!.plus(15)
        }
    }
}