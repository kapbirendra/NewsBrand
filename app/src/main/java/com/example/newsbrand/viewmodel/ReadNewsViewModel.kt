package com.example.newsbrand.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsbrand.response.Article
import javax.inject.Inject

class ReadNewsViewModel :ViewModel() {

     private val readLiveData = MutableLiveData<String>()

    val publicReadLiveData:LiveData<String>
        get() = readLiveData

    fun setReadData(article:String){
        readLiveData.postValue(article)
    }

}