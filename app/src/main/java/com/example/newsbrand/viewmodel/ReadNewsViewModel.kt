package com.example.newsbrand.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsbrand.response.news_module.Article
import javax.inject.Inject

class ReadNewsViewModel @Inject constructor():ViewModel() {

     private val readLiveData = MutableLiveData<Article>()

    val publicReadLiveData:LiveData<Article>
        get() = readLiveData

    fun setReadData(articlePosition: Article){
        readLiveData.postValue(articlePosition)
    }


}