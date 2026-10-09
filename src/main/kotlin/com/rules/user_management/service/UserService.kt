package com.rules.user_management.service

import SystemUser
import com.rules.user_management.exception.UserNotFoundException
import com.rules.user_management.repository.UserRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    private val logger = KotlinLogging.logger("The Name")

    fun getAllUsers(): List<SystemUser> =
        userRepository.findAll().toList()

    fun createUser(user: SystemUser): SystemUser {
        return userRepository.save(user)
    }

    fun getUserById(userId: Long): Optional<SystemUser> {
        return userRepository.findById(userId)
    }

    fun getUserByLogin(userLogin: String): SystemUser? {
        return userRepository.findByLogin(userLogin)
    }

    fun updateUserById(userId: Long, user: SystemUser): SystemUser {

        val existingUser = userRepository.findById(userId).orElse(null)
            ?: throw UserNotFoundException("User not found with id: $userId")

        val updatedUser = existingUser.copy(
            login = user.login,
            firstname = user.firstname,
            lastname = user.lastname,
            description = user.description
        )
        userRepository.save(updatedUser)
        return updatedUser
    }

    fun deleteUserById(userId: Long) {
        if (!userRepository.existsById(userId)) {
            throw UserNotFoundException("User not found with id: $userId")
        }
        userRepository.deleteById(userId)

    }
}