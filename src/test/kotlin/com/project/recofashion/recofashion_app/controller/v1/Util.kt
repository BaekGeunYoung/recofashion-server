package com.project.recofashion.recofashion_app.controller.v1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.entity.user.Role
import com.project.recofashion.recofashion_app.entity.user.Skin
import com.project.recofashion.recofashion_app.entity.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class Util {
    companion object {
        val mapper: ObjectMapper = ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false)
        val writer: ObjectWriter = mapper.writer().withDefaultPrettyPrinter()

        fun makeUser(mockMvc: MockMvc): User {
            val user = User(null, "dvmflstm", "dkdltm123", "firstName", "lastName",
                    mutableSetOf(Role.ADMIN), Skin.DARK, mutableSetOf(Color(0, 0, 0), Color(127, 127, 127)))

            val requestBody = writer.writeValueAsString(user)

            mockMvc.perform(
                    MockMvcRequestBuilders.post("/api/v1/user/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isOk)

            return user
        }

        fun makeAuthString(user: User, jwtTokenProvider: JwtTokenProvider): String {
            val token = jwtTokenProvider.createToken(user.username, user.roles.map {it.name})
            return "Bearer $token"
        }
    }
}