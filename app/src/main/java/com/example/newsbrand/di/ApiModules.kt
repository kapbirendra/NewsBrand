package com.example.newsbrand.di

import com.example.newsbrand.api.ApiService
import com.example.newsbrand.repository.ApiRepository
import com.example.newsbrand.utils.ConstantValues.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModules {

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
    fun provideRepository(apiService: ApiService): ApiRepository  = ApiRepository(apiService)
}