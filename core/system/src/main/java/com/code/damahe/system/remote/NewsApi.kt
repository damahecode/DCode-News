package com.code.damahe.system.remote

import com.code.damahe.system.app.Config.Remote.API_KEY
import com.code.damahe.system.model.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}