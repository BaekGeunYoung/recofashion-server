package com.project.recofashion.recofashion_app.service

import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.entity.user.User
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetails

interface UserService {
    fun signUp(userData: User): User

    fun signIn(username: String,
               password: String,
               authenticationManager: AuthenticationManager,
               jwtTokenProvider: JwtTokenProvider): MutableMap<String, Any>

    fun myInfo(userDetails: UserDetails): User

    fun findUser(userId: Long): User

    fun updateFavoriteColors(userDetails: UserDetails, colors: Set<Color>): Unit
}