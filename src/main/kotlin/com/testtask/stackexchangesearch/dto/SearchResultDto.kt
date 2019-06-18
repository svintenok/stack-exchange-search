package com.testtask.stackexchangesearch.models

data class SearchResultDto(
        val has_more: Boolean,
        val quota_remaining: Int,
        val items: List<ItemDto>)
