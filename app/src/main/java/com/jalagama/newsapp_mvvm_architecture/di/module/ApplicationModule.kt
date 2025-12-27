package com.jalagama.newsapp_mvvm_architecture.di.module

import android.content.Context
import com.jalagama.newsapp_mvvm_architecture.MyApplication
import com.jalagama.newsapp_mvvm_architecture.data.api.NetworkService
import com.jalagama.newsapp_mvvm_architecture.di.ApplicationContext
import com.jalagama.newsapp_mvvm_architecture.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: MyApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String {
        return "https://newsapi.org/v2/"
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

}