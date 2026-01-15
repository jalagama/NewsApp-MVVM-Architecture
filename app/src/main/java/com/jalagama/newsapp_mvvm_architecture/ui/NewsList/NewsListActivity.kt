package com.jalagama.newsapp_mvvm_architecture.ui.NewsList

import android.content.Context
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
import com.jalagama.newsapp_mvvm_architecture.databinding.ActivityNewsListBinding
import com.jalagama.newsapp_mvvm_architecture.di.component.DaggerActivityComponent
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import com.jalagama.newsapp_mvvm_architecture.ui.base.UiState
import com.jalagama.newsapp_mvvm_architecture.ui.topheadline.TopHeadlineAdapter
import com.jalagama.newsapp_mvvm_architecture.utils.AppConstants.NEWS_LIST_STATE_KEY
import com.jalagama.newsapp_mvvm_architecture.utils.AppConstants.NEWS_LIST_TYPE_KEY
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsListBinding

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    @Inject
    lateinit var newsListViewModel: NewsListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val state = intent.getStringExtra(NEWS_LIST_STATE_KEY)
        val type = intent.getStringExtra(NEWS_LIST_TYPE_KEY)

        when (type) {
            NewsType.NEWS_SOURCE.name -> {
                newsListViewModel.getNewsListBySource(state!!)
            }

            NewsType.NEWS_LANGUAGE.name -> {
                newsListViewModel.getNewsListByLanguage(state!!)
            }

            NewsType.NEWS_COUNTRIES.name -> {
                newsListViewModel.getNewsListByCountry(state!!)
            }

        }

        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsListViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvNoResults.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            if (it.data.isEmpty()) {
                                binding.tvNoResults.visibility = View.VISIBLE
                                binding.recyclerView.visibility = View.GONE
                            } else {
                                adapter.addData(it.data)
                                adapter.notifyDataSetChanged()
                            }


                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                            binding.tvNoResults.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.GONE
                            binding.tvNoResults.visibility = View.GONE
                            Toast.makeText(this@NewsListActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }

    companion object {
        fun newIntent(context: Context, state: String, type: String): Intent {
            var intent = Intent(context, NewsListActivity::class.java)
            intent.putExtra(NEWS_LIST_STATE_KEY, state)
            intent.putExtra(NEWS_LIST_TYPE_KEY, type)
            return intent
        }
    }
}