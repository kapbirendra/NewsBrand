package com.example.newsbrand.di

import android.content.Context
import androidx.room.Room
import com.example.newsbrand.repository.SavedArticleRepository
import com.example.newsbrand.rooms.saved.SavedArticleDb
import com.example.newsbrand.rooms.saved.SavedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SavedModule {

    @Provides
    @Singleton
    fun provideSaveDatabase(@ApplicationContext context: Context): SavedArticleDb =
        Room.databaseBuilder(context, SavedArticleDb::class.java,"saved_table")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideArticleDao(savedArticleDb: SavedArticleDb): SavedDao = savedArticleDb.savedArticleFromDb()

    @Provides
    @Singleton
    fun provideRepository(savedDao: SavedDao): SavedArticleRepository = SavedArticleRepository(savedDao)

}