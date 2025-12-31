package com.jalagama.newsapp_mvvm_architecture.di.component

import com.jalagama.newsapp_mvvm_architecture.di.ActivityScope
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import com.jalagama.newsapp_mvvm_architecture.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules  = [ ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)
}