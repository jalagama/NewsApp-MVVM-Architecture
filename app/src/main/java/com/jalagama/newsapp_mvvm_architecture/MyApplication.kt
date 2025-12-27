package com.jalagama.newsapp_mvvm_architecture

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
            super.onCreate()
    }

    companion object {
       const val TAG = "MyApplication"
    }
}