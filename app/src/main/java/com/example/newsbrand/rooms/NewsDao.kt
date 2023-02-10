package com.example.newsbrand.rooms

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsbrand.response.Article

@Dao()
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: List<Article>)

    @Delete
    suspend fun deleteNews(article: Article)

    @Query("SELECT * FROM articles_table")
    fun getNewsDataForOffline():List<Article>


}