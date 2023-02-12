package com.example.newsbrand.response.saved_response

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "saved_table",indices =  [Index(value = ["id"], unique = true)])
data class SavedArticle(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceSaved: SourceSaved,
    val title: String?,
    val url: String?,
    val urlToImage: String?
): Serializable