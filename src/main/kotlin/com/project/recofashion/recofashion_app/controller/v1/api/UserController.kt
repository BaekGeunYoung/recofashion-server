package com.project.recofashion.recofashion_app.controller.v1.api

import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.controller.v1.request.UserLoginRequest
import com.project.recofashion.recofashion_app.entity.user.User
import com.project.recofashion.recofashion_app.service.HistoryService
import com.project.recofashion.recofashion_app.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user")
class UserController(
        @Autowired
        private val userService: UserService,
        @Autowired
        private val historyService: HistoryService,
        @Autowired
        private val authenticationManager: AuthenticationManager,
        @Autowired
        private val jwtTokenProvider: JwtTokenProvider
) {
    @PostMapping("/register")
    fun register(@RequestBody @Valid userData: User): User = userService.signUp(userData)

    @PostMapping("/login")
    fun login(@RequestBody @Valid userLoginRequest: UserLoginRequest): MutableMap<String, Any>
     = userService.signIn(userLoginRequest.username, userLoginRequest.password, authenticationManager, jwtTokenProvider)

    @GetMapping("/me")
    fun myInfo(@AuthenticationPrincipal userDetails: UserDetails): MutableMap<String, Any> {
        val user = userService.myInfo(userDetails)
        val histories = historyService.getMyHistories(userDetails.username)

        val ret : MutableMap<String, Any> = HashMap()

        ret["user"] = user
        ret["histories"] = histories

        return ret
    }
}