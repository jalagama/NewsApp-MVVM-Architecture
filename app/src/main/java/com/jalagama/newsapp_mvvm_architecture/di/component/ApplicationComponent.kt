package com.jalagama.newsapp_mvvm_architecture.di.component

import android.content.Context
import com.jalagama.newsapp_mvvm_architecture.MyApplication
import com.jalagama.newsapp_mvvm_architecture.data.api.NetworkService
import com.jalagama.newsapp_mvvm_architecture.data.repository.NewsSearchRepository
import com.jalagama.newsapp_mvvm_architecture.data.repository.NewsSourceRepository
import com.jalagama.newsapp_mvvm_architecture.data.repository.TopHeadlineRepository
import com.jalagama.newsapp_mvvm_architecture.di.ApplicationContext
import com.jalagama.newsapp_mvvm_architecture.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(myApplication: MyApplication)


    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

    fun getNewsSourceRepository(): NewsSourceRepository

    fun getNewsSearchRepository(): NewsSearchRepository

}