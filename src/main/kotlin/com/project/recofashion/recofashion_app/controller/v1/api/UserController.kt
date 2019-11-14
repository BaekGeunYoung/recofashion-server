package com.project.recofashion.recofashion_app.controller.v1.api

import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.entity.user.User
import com.project.recofashion.recofashion_app.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user")
class UserController(
        @Autowired
        private val userService: UserService,
        @Autowired
        private val authenticationManager: AuthenticationManager,
        @Autowired
        private val jwtTokenProvider: JwtTokenProvider
) {
    @PostMapping("/register")
    fun register(@RequestBody @Valid userData: User): User {

    }
}