package com.rules.user_management.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfiguration {

    //http://localhost:8080/swagger-ui/index.html
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Demo API")
                    .version("1.0.0")
                    .description("This is a demo API documented with Swagger and SpringDoc.")
            )
    }
}