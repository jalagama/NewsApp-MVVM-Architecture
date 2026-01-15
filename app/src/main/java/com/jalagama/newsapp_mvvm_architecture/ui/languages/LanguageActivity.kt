package com.jalagama.newsapp_mvvm_architecture.ui.languages

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jalagama.newsapp_mvvm_architecture.MyApplication
import com.jalagama.newsapp_mvvm_architecture.databinding.ActivityLanguageBinding
import com.jalagama.newsapp_mvvm_architecture.di.component.DaggerActivityComponent
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import javax.inject.Inject

class LanguageActivity : AppCompatActivity() {


    lateinit var binding: ActivityLanguageBinding

    @Inject
    lateinit var adapter: LanguagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()

    }

    private fun setupUI(){
        val recyclerView = binding.recyclerViewLanguages
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder().applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }


    companion object {
        private const val TAG = "LanguageActivity"
        fun newIntent(activity: AppCompatActivity) = Intent(activity, LanguageActivity::class.java)
    }
}