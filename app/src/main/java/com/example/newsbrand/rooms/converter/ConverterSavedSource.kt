package com.example.newsbrand.rooms.converter

import androidx.room.TypeConverter
import com.example.newsbrand.response.saved_response.SourceSaved

class ConverterSavedSource {

    @TypeConverter
    fun fromSource(source: SourceSaved):String{
        return source.name!!
    }

    @TypeConverter
    fun toSource(name: String):SourceSaved {
        return SourceSaved(name,name)
    }
}