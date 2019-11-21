package com.project.recofashion.recofashion_app.controller.v1

import com.project.recofashion.recofashion_app.config.security.jwt.JwtTokenProvider
import com.project.recofashion.recofashion_app.controller.v1.Util.Companion.makeAuthString
import com.project.recofashion.recofashion_app.controller.v1.request.RecommendRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RecommendControllerTest(
        @Autowired private val jwtTokenProvider: JwtTokenProvider,
        @Autowired private val mockMvc: MockMvc
) {
    @Test
    fun recommendTest() {
        val user = Util.makeUser(mockMvc)
        val authToken = makeAuthString(user, jwtTokenProvider)

        val req = Util.writer.writeValueAsString(RecommendRequest(15, "PASTEL"))

        mockMvc.perform(post("/api/v1/recommend/")
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andExpect(jsonPath("clothes").exists())
                .andExpect(jsonPath("mainColor").exists())
                .andExpect(jsonPath("sideColors").exists())
                .andExpect(status().isOk)
                .andReturn()
    }
}