package com.example.newsbrand.rooms.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsbrand.response.Source
import com.example.newsbrand.response.saved_response.SourceSaved
import dagger.Provides
import javax.inject.Singleton
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