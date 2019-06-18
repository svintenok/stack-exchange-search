package com.testtask.stackexchangesearch.models

import java.util.*

data class Question(
        val isAnswered: Boolean,
        val answerCount: Int,
        val creationDate: Date,
        val lastActivityDate: Date,
        val link: String,
        val title: String,
        val author: String)
{
    private val sdf = java.text.SimpleDateFormat("MM/dd/yyyy")
    val creationDateString: String
        get() = sdf.format(creationDate)
    val lastActivityDateString: String
        get() = sdf.format(lastActivityDate)
}