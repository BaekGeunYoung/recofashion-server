package com.project.recofashion.recofashion_app.service.impl

import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.entity.user.User
import com.project.recofashion.recofashion_app.repository.ColorRepository
import com.project.recofashion.recofashion_app.repository.UserRepository
import com.project.recofashion.recofashion_app.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.security.sasl.AuthenticationException

@Service
class UserServiceImpl(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val colorRepository: ColorRepository,
        @Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserService {
    override fun signUp(userData: User): User {
        val userPasswordEncoded = User(
                null,
                userData.username,
                bCryptPasswordEncoder.encode(userData.password),
                userData.firstName,
                userData.lastName,
                userData.roles,
                userData.skin,
                userData.favoriteColors
                )

        for(color in userData.favoriteColors) if(!colorRepository.existsById(color)) colorRepository.save(color)
        return userRepository.save(userPasswordEncoded)
    }

    override fun signIn(username: String, password: String, authenticationManager: AuthenticationManager, jwtTokenProvider: JwtTokenProvider): MutableMap<String, Any> {
        val user: User? = userRepository.findByUsername(username)
        user?: throw UsernameNotFoundException("cannot find such user : $username")

        try{
            val authenticator = UsernamePasswordAuthenticationToken(username, password)
            authenticationManager.authenticate(authenticator)
            val token: String = jwtTokenProvider.createToken(username, user.roles.map { it.name }.toList())

            val ret: MutableMap<String, Any> = HashMap()
            ret["username"] = username
            ret["token"] = token

            return ret
        }
        catch (e: AuthenticationException) {
            throw BadCredentialsException("invalid username/password supplied")
        }
    }

    override fun myInfo(userDetails: UserDetails): User {
        return userRepository.findByUsername(userDetails.username)!!
    }

    override fun findUser(userId: Long): User {
        val user = userRepository.findById(userId)
        if(!user.isPresent) throw UsernameNotFoundException("cannot find such user")

        return user.get()
    }
}