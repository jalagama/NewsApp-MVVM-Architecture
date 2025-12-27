package com.jalagama.newsapp_mvvm_architecture.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.jalagama.newsapp_mvvm_architecture.di.ActivityContext
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
}