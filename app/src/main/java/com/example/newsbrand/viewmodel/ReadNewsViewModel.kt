package com.example.newsbrand.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsbrand.response.Article
import com.example.newsbrand.rooms.NewsDao
import kotlinx.coroutines.launch
import java.text.FieldPosition
import javax.inject.Inject

class ReadNewsViewModel @Inject constructor():ViewModel() {

     private val readLiveData = MutableLiveData<Int>()

    val publicReadLiveData:LiveData<Int>
        get() = readLiveData

    fun setReadData(articlePosition: Int){
        readLiveData.postValue(articlePosition)
    }


}