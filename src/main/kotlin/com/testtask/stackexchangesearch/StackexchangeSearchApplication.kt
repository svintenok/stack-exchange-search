package com.testtask.stackexchangesearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate



@SpringBootApplication
class StackexchangesearchApplication {
    @Bean
    fun getRestTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        // for gsip uncompressing
        restTemplate.requestFactory = HttpComponentsClientHttpRequestFactory()
        return restTemplate
    }
}

fun main(args: Array<String>) {
    runApplication<StackexchangesearchApplication>(*args)
}
