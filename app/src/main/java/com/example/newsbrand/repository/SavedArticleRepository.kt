package com.example.newsbrand.repository

import androidx.lifecycle.LiveData
import com.example.newsbrand.response.saved_response.SavedArticle
import com.example.newsbrand.rooms.saved.SavedDao
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SavedArticleRepository @Inject constructor (private val savedDao: SavedDao){
    fun readSavedData(): LiveData<List<SavedArticle>> {
        return savedDao.getSavedDataForOffline()
    }

    suspend fun addArticle(saved: SavedArticle){
        savedDao.insertNews(saved)
    }

    suspend fun deleteArticle(saved: SavedArticle){
        savedDao.deleteNews(saved)
    }
    suspend fun deleteAllArticle(){
        savedDao.deleteAll()
    }
    suspend fun updateSavedNews(saved: SavedArticle){
        savedDao.updateSavedNews(saved)
    }

}