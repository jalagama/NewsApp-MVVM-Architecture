package com.jalagama.newsapp_mvvm_architecture.ui.homeScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.jalagama.newsapp_mvvm_architecture.MyApplication
import com.jalagama.newsapp_mvvm_architecture.databinding.ActivityHomeBinding
import com.jalagama.newsapp_mvvm_architecture.di.component.DaggerActivityComponent
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import com.jalagama.newsapp_mvvm_architecture.ui.countries.CountriesActivity
import com.jalagama.newsapp_mvvm_architecture.ui.languages.LanguageActivity
import com.jalagama.newsapp_mvvm_architecture.ui.newsSource.NewsSourceActivity
import com.jalagama.newsapp_mvvm_architecture.ui.topheadline.TopHeadlineActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        injectDependencies()


        homeBinding.topHeadlinesButton.setOnClickListener {
            startActivity(TopHeadlineActivity.newIntent(this))
        }

        homeBinding.newsSourceButton.setOnClickListener {
            startActivity(NewsSourceActivity.newIntent(this))
        }

        homeBinding.countriesButton.setOnClickListener {
            startActivity(CountriesActivity.newIntent(this))
        }

        homeBinding.languagesButton.setOnClickListener {
            startActivity(LanguageActivity.newIntent(this))
        }

        homeBinding.searchButton.setOnClickListener {

        }
    }


    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }
}