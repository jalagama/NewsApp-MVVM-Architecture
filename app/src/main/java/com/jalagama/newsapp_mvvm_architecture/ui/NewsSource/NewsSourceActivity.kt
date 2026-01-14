package com.jalagama.newsapp_mvvm_architecture.ui.NewsSource

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jalagama.newsapp_mvvm_architecture.MyApplication
import com.jalagama.newsapp_mvvm_architecture.databinding.ActivityNewsSourceBinding
import com.jalagama.newsapp_mvvm_architecture.di.component.DaggerActivityComponent
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import com.jalagama.newsapp_mvvm_architecture.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceActivity : AppCompatActivity() {


    private lateinit var binding: ActivityNewsSourceBinding

    @Inject
    lateinit var newsSourceViewModel: NewsSourceViewModel

    @Inject
    lateinit var adapter: NewsSourceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerViewNewsSource
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                newsSourceViewModel.uiState.collect {
                    when (it) {

                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.addData(it.data)
                            binding.recyclerViewNewsSource.visibility = View.VISIBLE
                            adapter.notifyDataSetChanged()
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerViewNewsSource.visibility = View.GONE

                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@NewsSourceActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }

                    }
                }

            }

        }

    }


    fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    companion object {
        fun newIntent(activity: AppCompatActivity) =
            Intent(activity, NewsSourceActivity::class.java)
    }
}