package com.jalagama.newsapp_mvvm_architecture.ui.countries

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jalagama.newsapp_mvvm_architecture.MyApplication
import com.jalagama.newsapp_mvvm_architecture.databinding.ActivityCountriesBinding
import com.jalagama.newsapp_mvvm_architecture.di.component.DaggerActivityComponent
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import javax.inject.Inject

class CountriesActivity : AppCompatActivity() {

    lateinit var binding: ActivityCountriesBinding


    @Inject
    lateinit var countriesAdapter: CountriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerViewCountries
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = countriesAdapter
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }

    companion object {
        private const val TAG = "CountriesActivity"
        fun newIntent(activity: AppCompatActivity) = Intent(
            activity,
            CountriesActivity::class.java
        )
    }
}