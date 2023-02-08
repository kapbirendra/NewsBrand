package com.example.newsbrand.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsbrand.repository.ApiRepository
import com.example.newsbrand.response.Article
import androidx.lifecycle.viewModelScope
import com.example.newsbrand.response.NewsData
import com.example.newsbrand.response.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: ApiRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBreakingNews()
        }
    }
    val publicLivedata: LiveData<NewsData>
        get() = repository.quoteLiveFromRepository
}