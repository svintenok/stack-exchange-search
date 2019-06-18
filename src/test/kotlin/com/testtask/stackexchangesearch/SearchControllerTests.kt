package com.testtask.stackexchangesearch

import com.testtask.stackexchangesearch.controllers.SearchController
import com.testtask.stackexchangesearch.models.Question
import com.testtask.stackexchangesearch.services.QuestionService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@WebMvcTest(SearchController::class)
internal class SearchControllerTests {

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun service() = mockk<QuestionService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var service: QuestionService

    @BeforeEach
    fun serviceMockInit(){
        val searchString = "java"
        val pagesize = 25
        val expectedResult = ArrayList<Question>()
        every { service.getQuestions(searchString, pagesize, 1) } returns expectedResult
        every { service.getQuestions(searchString, pagesize, 2) } returns expectedResult
    }


    @Test
    fun `Search return search page`() {
        mockMvc.perform(get("/search"))
                .andExpect(status().isOk)
                .andExpect(content().contentType("text/html;charset=UTF-8"))
    }

    @Test
    fun `Search with param return search page`() {

        mockMvc.perform(get("/search?searchString=java"))
                .andExpect(status().isOk)
                .andExpect(content().contentType("text/html;charset=UTF-8"))
    }

    @Test
    fun `Search-json return ResultSearch json`() {
        mockMvc.perform(get("/search-json?searchString=java&page=2"))
                .andExpect(status().isOk)
                .andExpect(content().contentType("application/json;charset=UTF-8"))
    }
}