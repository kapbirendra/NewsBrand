package com.example.newsbrand.rooms

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsbrand.response.Article
import javax.inject.Inject

@Dao()
interface NewsDao {

    @Insert
    suspend fun insertNews(article: List<Article>)

    @Delete
    suspend fun deleteNews(article: Article)

    @Query("SELECT * FROM articles_table")
    fun getNewsDataForOffline():List<Article>
}