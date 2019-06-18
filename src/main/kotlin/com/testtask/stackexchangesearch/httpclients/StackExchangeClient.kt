package com.testtask.stackexchangesearch.httpclients

import com.testtask.stackexchangesearch.models.SearchResultDto


interface StackExchangeClient {
    fun inTitleSearch(searchString: String, pageSize: Int, page: Int): SearchResultDto?
}