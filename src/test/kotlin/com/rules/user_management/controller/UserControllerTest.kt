package com.rules.user_management.controller

import SystemUser
import com.ninjasquad.springmockk.MockkBean
import com.rules.user_management.service.UserService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(UserController::class)
class UserControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var userService: UserService

    @Configuration
    @ComponentScan("com.rules.user_management.repository", "com.rules.user_management.controller")
    public class TestConfig {
    }

    @Test
    fun `List users`() {
        val johnDoe = SystemUser(null, "johnDoe", "John", "Doe")
        val janeDoe = SystemUser(null, "janeDoe", "Jane", "Doe")
        every { userService.getAllUsers() } returns listOf(johnDoe, janeDoe)
        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[1].login").value(janeDoe.login))
    }
}