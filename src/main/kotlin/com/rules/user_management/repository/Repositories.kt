package com.rules.user_management.repository

import SystemUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<SystemUser, Long> {
    fun findByLogin(login: String): SystemUser?
}