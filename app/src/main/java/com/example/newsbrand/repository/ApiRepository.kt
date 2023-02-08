package com.example.newsbrand.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsbrand.api.ApiService
import com.example.newsbrand.response.NewsData
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ApiRepository @Inject constructor(private val apiService: ApiService) {

    private val quotesLiveData = MutableLiveData<NewsData>()

    suspend fun getBreakingNews(){
        val result =apiService.getBreakingNews()
        if (result.body() != null){
            quotesLiveData.postValue(result.body())
        }else return
    }

    val quoteLiveFromRepository: LiveData<NewsData>
        get() = quotesLiveData
}