package com.project.recofashion.recofashion_app.controller.v1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.databind.SerializationFeature
import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.controller.v1.request.UserLoginRequest
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest(
        @Autowired private val mockMvc: MockMvc,
        @Autowired private val jwtTokenProvider: JwtTokenProvider
) {
    private final val mapper: ObjectMapper = ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false)
    private final val writer: ObjectWriter = mapper.writer().withDefaultPrettyPrinter()

    val user = User(null, "dvmflstm", "dkdltm123", "firstName", "lastName",
            mutableSetOf(Role.ADMIN), Skin.DARK, mutableSetOf(Color(1, 2, 3), Color(127, 128, 17)))
    @Test
    fun registerAndLoginTest() {
        val requestBody = writer.writeValueAsString(user)

        mockMvc.perform(
                post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk)
                .andExpect(jsonPath("username").value("dvmflstm"))
                .andExpect(jsonPath("firstName").value("firstName"))
                .andExpect(jsonPath("lastName").value("lastName"))

        //로그인 테스트
        //1. 로그인 성공 테스트
        var loginInfo = UserLoginRequest("dvmflstm", "dkdltm123")
        var req = writer.writeValueAsString(loginInfo)

        mockMvc.perform(
                post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().isOk)

        //2. 로그인 실패 테스트
        loginInfo = UserLoginRequest("dvmflstm", "dkdltm1234")
        req = writer.writeValueAsString(loginInfo)

        mockMvc.perform(
                post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().isForbidden)

        loginInfo = UserLoginRequest("dvmflstm123", "dkdltm123")
        req = writer.writeValueAsString(loginInfo)

        mockMvc.perform(
                post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().isForbidden)
    }

    @Test
    fun myInfoTest() {
        val requestBody = writer.writeValueAsString(user)

        mockMvc.perform(
                post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk)

        //내정보 열람 테스트
        val token = jwtTokenProvider.createToken(user.username, user.roles.map {it.name})
        val authHeader = "Bearer $token"

        mockMvc.perform(
                get("/api/v1/user/me")
                        .header("Authorization", authHeader))
                .andExpect(status().isOk)
                .andExpect(jsonPath("user").exists())
                .andExpect(jsonPath("histories").exists())
                .andReturn()
    }
}