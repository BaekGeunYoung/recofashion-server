package com.project.recofashion.recofashion_app.config.security.userdetails

import com.project.recofashion.recofashion_app.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(@Autowired private val userRepository: UserRepository): UserDetailsService
{
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        user?: throw UsernameNotFoundException("cannot find such username: $username")

        return CustomUserDetails(user)
    }
}