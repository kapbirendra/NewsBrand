package com.example.newsbrand.response

data class NewsData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>,
)