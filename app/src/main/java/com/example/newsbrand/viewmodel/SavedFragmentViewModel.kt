package com.example.newsbrand.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsbrand.repository.SavedArticleRepository
import com.example.newsbrand.response.Article
import com.example.newsbrand.response.saved_response.SavedArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedFragmentViewModel @Inject constructor(private val savedArticleRepository: SavedArticleRepository):ViewModel() {

    fun addArticlesFromVm(savedArticle: SavedArticle){
        viewModelScope.launch(Dispatchers.IO) {
            savedArticleRepository.addArticle(savedArticle)
        }
    }

    fun deleteArticleFromVm(savedArticle: SavedArticle){
        viewModelScope.launch(Dispatchers.IO) {
            savedArticleRepository.deleteArticle(savedArticle)
        }
    }
    fun updateArticleFromMvm(savedArticle: SavedArticle){
        viewModelScope.launch(Dispatchers.IO) {
            savedArticleRepository.updateSavedNews(savedArticle)
        }
    }

    fun deleteAllArticle(){
        viewModelScope.launch (Dispatchers.IO){
            savedArticleRepository.deleteAllArticle()
        }
    }

    fun readSavedArticleFromVm(): LiveData<List<SavedArticle>> {
        return savedArticleRepository.readSavedData()
    }


}