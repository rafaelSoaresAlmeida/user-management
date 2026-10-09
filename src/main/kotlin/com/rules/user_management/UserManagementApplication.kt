package com.rules.user_management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan("com.rules.user_management.*")
@SpringBootApplication
class UserManagementApplication

fun main(args: Array<String>) {
    runApplication<UserManagementApplication>(*args)

}
