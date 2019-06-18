package com.testtask.stackexchangesearch.services

import com.testtask.stackexchangesearch.models.Question


interface QuestionService {
    fun getQuestions(searchString: String, pageSize: Int, page: Int): List<Question>?
}