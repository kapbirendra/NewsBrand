package com.example.newsbrand.api

import com.example.newsbrand.response.Article
import com.example.newsbrand.response.NewsData
import com.example.newsbrand.utils.ConstantValues.API_KEY
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(value = "v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country:String = "us",
        @Query("apiKey")
        apiKey:String = API_KEY
    ) :Response<NewsData>
}