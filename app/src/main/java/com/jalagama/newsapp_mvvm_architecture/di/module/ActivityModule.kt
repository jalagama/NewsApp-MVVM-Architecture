package com.jalagama.newsapp_mvvm_architecture.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jalagama.newsapp_mvvm_architecture.data.repository.TopHeadlineRepository
import com.jalagama.newsapp_mvvm_architecture.di.ActivityContext
import com.jalagama.newsapp_mvvm_architecture.ui.base.ViewModelProviderFactory
import com.jalagama.newsapp_mvvm_architecture.ui.topheadline.TopHeadlineAdapter
import com.jalagama.newsapp_mvvm_architecture.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideTopHeadlineViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

}