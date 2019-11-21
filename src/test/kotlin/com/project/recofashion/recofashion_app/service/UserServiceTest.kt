package com.project.recofashion.recofashion_app.service

import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.config.security.userdetails.CustomUserDetails
import com.project.recofashion.recofashion_app.config.security.userdetails.CustomUserDetailsService
import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.entity.user.Role
import com.project.recofashion.recofashion_app.entity.user.Skin
import com.project.recofashion.recofashion_app.entity.user.User
import com.project.recofashion.recofashion_app.repository.ColorRepository
import com.project.recofashion.recofashion_app.repository.UserRepository
import com.project.recofashion.recofashion_app.service.impl.UserServiceImpl
import org.aspectj.lang.annotation.Before
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserServiceTest{
    @Mock private val userRepository: UserRepository? = null
    @Mock private val colorRepository: ColorRepository? = null
    @Mock private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null
    @Mock private val authenticationManager: AuthenticationManager? = null
    @InjectMocks private val userService: UserServiceImpl? = null

    @Autowired private val jwtTokenProvider: JwtTokenProvider? = null

    private val user = User(null, "dvmflstm", "dkdltm123", "firstName", "lastName",
            mutableSetOf(Role.ADMIN), Skin.DARK, mutableSetOf(Color(1, 2, 3), Color(127, 128, 17)))
    @Test
    fun signUpSuccessTest() {
        Mockito.`when`(bCryptPasswordEncoder!!.encode(anyString())).thenReturn("dkdltm123")
        Mockito.`when`(userRepository!!.save(user)).thenReturn(user)

        val result = userService!!.signUp(user)

        assertThat(result).isNotNull
        assertThat(result.firstName).isEqualTo(user.firstName)
        assertThat(result.lastName).isEqualTo(user.lastName)
        assertThat(result.username).isEqualTo(user.username)
    }

    @Test
    fun signInTest() {
        given(userRepository!!.findByUsername(anyString())).willReturn(user)

        val authenticator = UsernamePasswordAuthenticationToken(user.username, user.password)
        given(authenticationManager!!.authenticate(any())).willReturn(authenticator)

        val result = userService!!.signIn(user.username, user.password, authenticationManager, jwtTokenProvider!!)

        assertThat(result).isNotNull
        assertThat(result["username"]).isEqualTo(user.username)
        assertThat(result["token"]).isNotNull
    }

    @Test
    fun myInfoTest() {
        given(userRepository!!.findByUsername(anyString())).willReturn(user)
        val userDetails = CustomUserDetails(user)
        val result = userService!!.myInfo(userDetails)

        assertThat(result).isNotNull
        assertThat(result.firstName).isEqualTo(user.firstName)
        assertThat(result.lastName).isEqualTo(user.lastName)
        assertThat(result.username).isEqualTo(user.username)
    }
}