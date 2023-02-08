package com.example.newsbrand.rooms.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsbrand.response.Source
import dagger.Provides
import javax.inject.Singleton
class ConverterSource {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name!!
    }

    @TypeConverter
    fun toSource(name: String):Source {
        return Source(name,name)
    }
}