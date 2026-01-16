package com.jalagama.newsapp_mvvm_architecture.di.component

import com.jalagama.newsapp_mvvm_architecture.di.ActivityScope
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import com.jalagama.newsapp_mvvm_architecture.ui.search.SearchActivity
import com.jalagama.newsapp_mvvm_architecture.ui.countries.CountriesActivity
import com.jalagama.newsapp_mvvm_architecture.ui.homeScreen.HomeActivity
import com.jalagama.newsapp_mvvm_architecture.ui.languages.LanguageActivity
import com.jalagama.newsapp_mvvm_architecture.ui.newsList.NewsListActivity
import com.jalagama.newsapp_mvvm_architecture.ui.newsSource.NewsSourceActivity
import com.jalagama.newsapp_mvvm_architecture.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

    fun inject(activity: HomeActivity)

    fun inject(activity: NewsSourceActivity)

    fun inject(activity: NewsListActivity)

    fun inject(activity: CountriesActivity)

    fun inject(activity: LanguageActivity)

    fun inject(activity: SearchActivity)
}