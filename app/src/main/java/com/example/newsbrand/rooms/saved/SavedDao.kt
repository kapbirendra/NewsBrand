package com.example.newsbrand.rooms.saved

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsbrand.response.saved_response.SavedArticle
import retrofit2.http.DELETE

@Dao
interface SavedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(savedArticle: SavedArticle)

    @Delete
    suspend fun deleteNews(savedArticle: SavedArticle)

    @Query("DELETE FROM saved_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateSavedNews(savedArticle: SavedArticle)

    @Query("SELECT * FROM saved_table")
    fun getSavedDataForOffline():LiveData<List<SavedArticle>>

}