package com.example.newsbrand.rooms.converter

import androidx.room.TypeConverter
import com.example.newsbrand.response.news_module.Source

class ConverterSource {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name!!
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name)
    }
}