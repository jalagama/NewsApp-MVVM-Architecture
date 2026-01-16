package com.jalagama.newsapp_mvvm_architecture.data.api

import com.jalagama.newsapp_mvvm_architecture.data.model.NewsSourceResponse
import com.jalagama.newsapp_mvvm_architecture.data.model.TopHeadlinesResponse
import com.jalagama.newsapp_mvvm_architecture.utils.AppConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {

    @Headers("X-Api-Key: $API_KEY", "User-Agent: ABC")
    @GET("top-headlines")
    suspend fun getTopHeadlinesByCountry(@Query("country") country: String): TopHeadlinesResponse


    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlinesBySource(@Query("sources") country: String): TopHeadlinesResponse


    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlinesByLanguage(@Query("language") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("sources")
    suspend fun getNewsSources(): NewsSourceResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun getNewsBySearch(@Query("q") country: String): TopHeadlinesResponse

}