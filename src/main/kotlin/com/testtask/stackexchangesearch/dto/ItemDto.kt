package com.testtask.stackexchangesearch.models

data class ItemDto(
        val is_answered: Boolean,
        val answer_count: Int,
        val creation_date: Long,
        val last_activity_date: Long,
        val link: String,
        val title: String,
        val owner: StackoverflowUserDto){

    fun toQuestion() = Question(
            isAnswered = is_answered,
            answerCount = answer_count,
            creationDate = java.util.Date(creation_date*1000),
            lastActivityDate = java.util.Date(last_activity_date*1000),
            link = link,
            title = title,
            author = owner.display_name
    )
}