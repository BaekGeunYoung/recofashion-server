package com.project.recofashion.recofashion_app.controller.v1.api

import com.project.recofashion.recofashion_app.controller.v1.request.RecommendRequest
import com.project.recofashion.recofashion_app.controller.v1.request.SearchRequest
import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.service.RecommendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/recommend")
class RecommendController(
        @Autowired private val recommendService: RecommendService
) {
    @PostMapping("/")
    fun recommend(
            @RequestBody @Valid request: RecommendRequest,
            @AuthenticationPrincipal userDetails: UserDetails
    ): MutableMap<String, Any> = recommendService.recommend(request.temperature, request.tone, userDetails)

    @PostMapping("/search")
    fun search(@RequestBody @Valid request: SearchRequest): MutableMap<String, Any> = recommendService.search(request.color, request.temperature)
}