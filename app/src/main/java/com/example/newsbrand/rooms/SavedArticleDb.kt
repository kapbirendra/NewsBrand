package com.example.newsbrand.rooms

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsbrand.response.saved_response.SavedArticle
import com.example.newsbrand.rooms.converter.ConverterSavedSource

@Database(entities = [SavedArticle::class], version = 2)
@TypeConverters(ConverterSavedSource::class)
abstract class SavedArticleDb:RoomDatabase() {
    abstract fun savedArticleFromDb():SavedDao
}