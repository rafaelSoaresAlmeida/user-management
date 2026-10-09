package com.rules.user_management.configuration

import SystemUser
import com.rules.user_management.repository.UserRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Database {

    private val logger = KotlinLogging.logger("The Name")

    @Bean
    fun databaseInitializer(
        userRepository: UserRepository
    ) = ApplicationRunner {
        val user = userRepository.save(SystemUser(null,"johnDoe", "John", "Doe", "batata desc"))
        println(user.toString())
    }

    @Bean
    fun testLogger(): Boolean {
        logger.trace { "This is trace log" }
        logger.debug { "This is debug log" }
        logger.info { "This is info log" }
        logger.warn { "This is warn log" }
        logger.error { "This is error log" }
        return true
    }
}