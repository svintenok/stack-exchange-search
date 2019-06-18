package com.testtask.stackexchangesearch.controllers

import com.testtask.stackexchangesearch.models.Question
import com.testtask.stackexchangesearch.services.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody


private const val pageSize = 25

@Controller
class SearchController {

    @Autowired
    lateinit var questionService: QuestionService

    @GetMapping("/search")
    fun searchPage(@RequestParam(value = "searchString", required = false) searchString: String?, model: Model): String {
        model["pageSize"] = pageSize
        if (searchString != null && searchString != "") {
            model["searchString"] = searchString
            val searchResult = questionService.getQuestions(searchString, pageSize, 1)
            if (searchResult != null) {
                model["questions"] = searchResult
            }
            else{
                model["error"] = "true"
            }
        }
        return "search_page"
    }

    @ResponseBody
    @GetMapping("/search-json")
    fun search(@RequestParam(value = "searchString") searchString: String, @RequestParam page: Int): List<Question>? {
        return questionService.getQuestions(searchString, pageSize, page)
    }

}