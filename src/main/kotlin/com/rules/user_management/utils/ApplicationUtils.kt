package com.rules.user_management.utils

import com.rules.user_management.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ApplicationUtils {

    fun checkException(e: Exception): Any {
        return e.takeIf { it is UserNotFoundException }
            ?.apply { ResponseEntity(null, HttpStatus.NOT_FOUND) }
            ?: run { ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR) }
    }
}