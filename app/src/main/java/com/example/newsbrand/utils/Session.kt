package com.example.newsbrand.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Session @Inject constructor (@ApplicationContext context: Context) {
    private val PREFERENCE_VALUE = "session"
    private val sharedPreferences = context.getSharedPreferences(PREFERENCE_VALUE,MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    fun setTheValue(key:String){
        editor.putString(PREFERENCE_VALUE,key)
        editor.apply()
    }

    fun getTheValue():String?{
        return sharedPreferences.getString(PREFERENCE_VALUE,null)
    }
}