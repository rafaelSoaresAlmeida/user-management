package com.rules.user_management.service

import SystemUser
import com.rules.user_management.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UserServiceTest {

    private val userRepository = mockk<UserRepository>()
    private val userService = UserService(userRepository)

    @Test
    fun testGetUserByLoginWithSuccess() {
        // Given
        val userDb = SystemUser(null, "johnDoe", "John", "Doe")

        every { userRepository.findByLogin(any()) } returns userDb

        // When
        val actual = userService.getUserByLogin("johnDoe")

        // Then
        assertEquals("John", actual?.firstname)
        verify(exactly = 1) {userRepository.findByLogin(any()) }
    }
}