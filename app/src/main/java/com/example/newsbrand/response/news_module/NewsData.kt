package com.example.newsbrand.response.news_module

data class NewsData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>,
)