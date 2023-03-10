package com.example.newsbrand.di

import android.content.Context
import androidx.room.Room
import com.example.newsbrand.api.ApiService
import com.example.newsbrand.repository.ApiRepository
import com.example.newsbrand.rooms.offline.NewsDao
import com.example.newsbrand.rooms.offline.RoomOfflineDb
import com.example.newsbrand.utils.ConstantValues.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModules {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): RoomOfflineDb =
        Room.databaseBuilder(context, RoomOfflineDb::class.java,"postDatabase")

            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideArticleDao(roomOfflineDb: RoomOfflineDb): NewsDao = roomOfflineDb.newsArticleFromDb()


    @Provides
    @Singleton
    fun baseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(baseurl:String) : ApiService =
        Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(@ApplicationContext context: Context,apiService: ApiService,newsDao: NewsDao): ApiRepository  = ApiRepository(newsDao,apiService,context)

//    @Provides
//    fun newOnclicked():
}