package com.testtask.stackexchangesearch.httpclients.impl

import com.testtask.stackexchangesearch.httpclients.StackExchangeClient
import com.testtask.stackexchangesearch.models.SearchResultDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate


private const val baseSearchURL = "http://api.stackexchange.com/2.2/search?" +
        "order=desc&sort=activity&site=stackoverflow"
private const val titleSearchParamString = "intitle"
private const val pageSizeParamString = "pagesize"
private const val pageNumParamString = "page"

@Component
class StackExchangeStackExchangeClientImpl : StackExchangeClient {

    @Autowired
    lateinit var restTemplate: RestTemplate

    override fun inTitleSearch(searchString: String, pageSize: Int, page: Int): SearchResultDto? {
        var searchResult: SearchResultDto? = null
        try {
            searchResult = restTemplate.getForObject(
                    "$baseSearchURL&$pageSizeParamString=$pageSize&" +
                            "$titleSearchParamString=$searchString&$pageNumParamString=$page",
                    SearchResultDto::class.java)
        }
        catch (e: HttpClientErrorException){
        }

        return searchResult
    }
}