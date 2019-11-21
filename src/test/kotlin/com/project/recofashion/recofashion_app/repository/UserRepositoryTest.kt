package com.project.recofashion.recofashion_app.repository

import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.entity.user.Role
import com.project.recofashion.recofashion_app.entity.user.Skin
import com.project.recofashion.recofashion_app.entity.user.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest(
        @Autowired private val userRepository: UserRepository
) {
    private val user = User(null, "dvmflstm", "dkdltm123", "firstName", "lastName",
            mutableSetOf(Role.ADMIN), Skin.DARK, mutableSetOf(Color(1, 2, 3), Color(127, 128, 17)))

    @BeforeAll
    fun setUp() {
        userRepository.save(user)
    }

    @Test
    fun findByUsername_success() {
        val result = userRepository.findByUsername(user.username)
        assertThat(result).isNotNull
    }

    @Test
    fun findByUsername_fail() {
        val result = userRepository.findByUsername("qweqwe")
        assertThat(result).isEqualTo(null)
    }
}