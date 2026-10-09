package com.rules.user_management.controller

import SystemUser
import com.rules.user_management.service.UserService
import com.rules.user_management.utils.ApplicationUtils
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @Operation(summary = "Get all users", description = "Returns a list of users")
    @GetMapping("/users")
    fun getAllUsers(): List<SystemUser> =
        userService.getAllUsers()

    @PostMapping("/user")
    fun createUser(@RequestBody user: SystemUser): Any {
        try {
            val createdUser = userService.createUser(user)
            return ResponseEntity(createdUser, HttpStatus.CREATED)
        } catch (e: Exception) {
            return ApplicationUtils.checkException(e)
        }
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable("id") userId: Long): ResponseEntity<SystemUser>? {
        return userService.getUserById(userId).map { user ->
            ResponseEntity.ok(user)
        }.orElse(notFound().build())


//        try {
//            return ResponseEntity(userService.getUserById(userId), HttpStatus.OK)
//        } catch (e: Exception) {
//            return ApplicationUtils.checkException(e)
//        }
    }

    @PutMapping("/user/{id}")
    fun updateUserById(@PathVariable("id") userId: Long, @RequestBody user: SystemUser):  Any {
        try {
            return ResponseEntity(userService.updateUserById(userId, user), HttpStatus.OK)
        } catch (e: Exception) {
            return ApplicationUtils.checkException(e)
        }
    }

    @DeleteMapping("/user/{id}")
    fun deleteUserById(@PathVariable("id") userId: Long):  Any {
        try {
            userService.deleteUserById(userId)
            return ResponseEntity(null,HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            return ApplicationUtils.checkException(e)
        }
    }
}