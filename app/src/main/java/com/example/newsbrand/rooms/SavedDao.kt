package com.example.newsbrand.rooms

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsbrand.response.saved_response.SavedArticle

@Dao
interface SavedDao {
    @Insert
    suspend fun insertNews(savedArticle: SavedArticle)

    @Delete
    suspend fun deleteNews(savedArticle: SavedArticle)

    @Query("SELECT * FROM saved_table")
    fun getSavedDataForOffline():LiveData<List<SavedArticle>>

}