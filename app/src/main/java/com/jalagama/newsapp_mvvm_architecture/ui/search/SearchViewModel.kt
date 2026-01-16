package com.jalagama.newsapp_mvvm_architecture.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalagama.newsapp_mvvm_architecture.data.model.Article
import com.jalagama.newsapp_mvvm_architecture.data.repository.NewsSearchRepository
import com.jalagama.newsapp_mvvm_architecture.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: NewsSearchRepository) :
    ViewModel() {

    private var _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    fun setQuery(newQuery: String) {
        _query.value = newQuery
    }

    init {
        viewModelScope.launch {
            query
                .debounce(300)
                .filter { it.isNotEmpty() }
                .flatMapLatest { query ->
                    repository.getSearchedNews(query)
                        .catch { e ->
                            emit(emptyList())
                            _uiState.value = UiState.Error(e.message ?: "Unknown Error")
                        }
                }
                .collect { articles ->
                    _uiState.value = UiState.Success(articles)
                }
        }
    }
}