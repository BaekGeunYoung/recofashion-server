package com.project.recofashion.recofashion_app.controller.v1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.controller.v1.request.AddHistoryRequest
import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.entity.user.Role
import com.project.recofashion.recofashion_app.entity.user.Skin
import com.project.recofashion.recofashion_app.entity.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class HistoryControllerTest(
        @Autowired private val mockMvc: MockMvc,
        @Autowired private val jwtTokenProvider: JwtTokenProvider
) {
    private final val mapper: ObjectMapper = ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false)
    private final val writer: ObjectWriter = mapper.writer().withDefaultPrettyPrinter()

    fun makeUser(): User {
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

    fun makeAuthString(user: User): String {
        val token = jwtTokenProvider.createToken(user.username, user.roles.map {it.name})
        return "Bearer $token"
    }

    @Test
    fun addHistoryTest() {
        val user = makeUser()
        val auth = makeAuthString(user)

        val req = AddHistoryRequest(Color(255, 255, 255), Color(1, 1, 1))
        val requestBody = writer.writeValueAsString(req)

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/histories/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .header("Authorization", auth))
                .andExpect(status().isOk)
                .andExpect(jsonPath("topColor").exists())
                .andExpect(jsonPath("pantsColor").exists())
                .andReturn()
    }
}