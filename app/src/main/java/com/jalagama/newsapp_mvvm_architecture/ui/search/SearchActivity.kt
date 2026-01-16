package com.jalagama.newsapp_mvvm_architecture.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jalagama.newsapp_mvvm_architecture.MyApplication
import com.jalagama.newsapp_mvvm_architecture.databinding.ActivitySearchBinding
import com.jalagama.newsapp_mvvm_architecture.di.component.DaggerActivityComponent
import com.jalagama.newsapp_mvvm_architecture.di.module.ActivityModule
import com.jalagama.newsapp_mvvm_architecture.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {


    lateinit var binding: ActivitySearchBinding

    @Inject
    lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var searchAdapter: NewsSearchResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupUIObservers()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerViewSearch
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = searchAdapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {
                    searchViewModel.setQuery(newText)
                }
                return true
            }
        })
    }

    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)

    }

    private fun setupUIObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                searchViewModel.uiState.collect {
                    when (it) {
                        is UiState.Loading -> {
                            binding.tvNoResults.visibility = GONE
                            binding.recyclerViewSearch.visibility = GONE
                        }

                        is UiState.Success -> {
                            binding.recyclerViewSearch.visibility = VISIBLE
                            searchAdapter.addData(it.data)
                            searchAdapter.notifyDataSetChanged()
                            if (it.data.isEmpty()){
                                binding.tvNoResults.visibility = VISIBLE
                                binding.recyclerViewSearch.visibility = GONE
                                return@collect
                            }
                            binding.tvNoResults.visibility = GONE
                        }

                        is UiState.Error -> {
                            binding.recyclerViewSearch.visibility = GONE
                            binding.tvNoResults.visibility = VISIBLE
                            Toast.makeText(this@SearchActivity, it.message, Toast.LENGTH_LONG)
                                .show()

                        }
                    }
                }

            }

        }
    }


    companion object {
        private const val TAG = "SearchActivity"

        fun newIntent(activity: AppCompatActivity) = Intent(
            activity,
            SearchActivity::class.java
        )
    }
}