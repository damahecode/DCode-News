package com.code.damahe.system.model.news

data class NewsResponse(
    val articles: List<Article> = emptyList(),
    val status: String = "",
    val totalResults: Int = 0
)