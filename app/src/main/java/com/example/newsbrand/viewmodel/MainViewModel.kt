package com.example.newsbrand.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsbrand.repository.ApiRepository
import androidx.lifecycle.viewModelScope
import com.example.newsbrand.response.news_module.NewsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: ApiRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBreakingNews()
        }
    }
    val publicLivedata: LiveData<NewsData>
        get() = repository.quoteLiveFromRepository

    //this is not working
}