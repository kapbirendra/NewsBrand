package com.example.newsbrand.rooms.offline

import androidx.room.*
import com.example.newsbrand.response.news_module.Article
import com.example.newsbrand.rooms.converter.ConverterSource

@Database(entities = [Article::class], version = 2)
@TypeConverters(ConverterSource::class)
abstract class RoomOfflineDb : RoomDatabase() {
    abstract fun newsArticleFromDb(): NewsDao

}