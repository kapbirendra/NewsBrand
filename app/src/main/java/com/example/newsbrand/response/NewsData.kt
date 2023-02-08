package com.example.newsbrand.response

import androidx.room.Entity

data class NewsData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>,
)