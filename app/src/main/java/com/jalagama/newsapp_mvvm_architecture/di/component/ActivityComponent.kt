package com.jalagama.newsapp_mvvm_architecture.di.component

import com.jalagama.newsapp_mvvm_architecture.di.ActivityScope
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import com.jalagama.newsapp_mvvm_architecture.di.module.ApplicationModule
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class, ApplicationModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadLineActivity)
}