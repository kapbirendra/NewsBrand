package com.example.newsbrand.rooms

import androidx.room.*
import com.example.newsbrand.response.Article
import com.example.newsbrand.rooms.converter.ConverterSavedSource
import com.example.newsbrand.rooms.converter.ConverterSource

@Database(entities = [Article::class], version = 2)
@TypeConverters(ConverterSource::class)
abstract class RoomOfflineDb : RoomDatabase() {
    abstract fun newsArticleFromDb(): NewsDao

}