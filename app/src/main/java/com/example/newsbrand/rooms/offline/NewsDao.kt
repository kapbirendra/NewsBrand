package com.example.newsbrand.rooms.offline

import androidx.room.*
import com.example.newsbrand.response.news_module.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: List<Article>)

    @Delete
    suspend fun deleteNews(article: Article)

    @Query("SELECT * FROM articles_table")
    fun getNewsDataForOffline():List<Article>


}