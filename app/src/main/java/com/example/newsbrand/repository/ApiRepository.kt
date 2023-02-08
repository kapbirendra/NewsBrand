package com.example.newsbrand.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsbrand.api.ApiService
import com.example.newsbrand.response.NewsData
import com.example.newsbrand.rooms.NewsDao
import com.example.newsbrand.utils.InternetCheck
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ApiRepository @Inject constructor(private val newsDao: NewsDao,private val apiService: ApiService,private val context:Context) {

    private val quotesLiveData = MutableLiveData<NewsData>()

    suspend fun getBreakingNews(){
        if (InternetCheck.isOnline(context)){
            val result =apiService.getBreakingNews()
            if (result.body() != null){
                newsDao.insertNews(result.body()!!.articles)
                quotesLiveData.postValue(result.body())
            }else return
        }else{
            val news = newsDao.getNewsDataForOffline()
            println("#### ${news[0].author}")
            val newsList = NewsData("1",1,news)
            quotesLiveData.postValue(newsList)
        }

    }

    val quoteLiveFromRepository: LiveData<NewsData>
        get() = quotesLiveData
}