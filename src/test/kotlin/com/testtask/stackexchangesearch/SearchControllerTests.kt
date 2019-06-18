package com.testtask.stackexchangesearch

import com.testtask.stackexchangesearch.controllers.SearchController
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@WebMvcTest(SearchController::class)
internal class SearchControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

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