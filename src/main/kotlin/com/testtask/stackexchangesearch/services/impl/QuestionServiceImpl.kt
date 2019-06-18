package com.testtask.stackexchangesearch.services.impl

import com.testtask.stackexchangesearch.httpclients.StackExchangeClient
import com.testtask.stackexchangesearch.models.Question
import com.testtask.stackexchangesearch.services.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionServiceImpl : QuestionService {

    @Autowired
    lateinit var stackExchangeClient : StackExchangeClient

    override fun getQuestions(searchString: String, pageSize: Int, page: Int): List<Question>? {
        val searchResultDto= stackExchangeClient.inTitleSearch(searchString, pageSize, page)
        return searchResultDto?.items?.map { itemDto ->  itemDto.toQuestion()}
    }
}