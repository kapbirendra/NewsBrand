package com.example.newsbrand.response

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)