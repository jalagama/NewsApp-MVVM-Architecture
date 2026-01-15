package com.jalagama.newsapp_mvvm_architecture.ui.topheadline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalagama.newsapp_mvvm_architecture.data.model.Article
import com.jalagama.newsapp_mvvm_architecture.data.repository.TopHeadlineRepository
import com.jalagama.newsapp_mvvm_architecture.ui.base.UiState
import com.jalagama.newsapp_mvvm_architecture.utils.AppConstants.COUNTRY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        Log.d(TAG,"fetchTopHeadlines")
        fetchTopHeadlines()
    }

    fun fetchTopHeadlines() {
       viewModelScope.launch {
           topHeadlineRepository.getTopHeadlinesByCountry(COUNTRY)
               .catch { e -> _uiState.value = UiState.Error(e.message ?: "Unknown Error")}
               .collect { _uiState.value = UiState.Success(it) }

       }
    }

    companion object {
        private const val TAG = "TopHeadlineViewModel"
    }
}