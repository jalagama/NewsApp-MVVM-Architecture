package com.jalagama.newsapp_mvvm_architecture.ui.NewsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalagama.newsapp_mvvm_architecture.data.model.Article
import com.jalagama.newsapp_mvvm_architecture.data.repository.TopHeadlineRepository
import com.jalagama.newsapp_mvvm_architecture.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel
@Inject constructor(private val topheadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    fun getNewsListByCountry(country: String) {
        viewModelScope.launch {
            topheadlineRepository.getTopHeadlinesByCountry(country)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message ?: "Unknown Error")
                }
                .collect { _uiState.value = UiState.Success(it) }
        }
    }

    fun getNewsListBySource(source: String) {
        viewModelScope.launch {
            topheadlineRepository.getTopHeadlinesBySource(source)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message ?: "Unknown Error")
                }
                .collect { _uiState.value = UiState.Success(it) }
        }
    }

    fun getNewsListByLanguage(language: String) {
        viewModelScope.launch {
            topheadlineRepository.getTopHeadlinesByLanguage(language)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message ?: "Unknown Error")
                }
                .collect { _uiState.value = UiState.Success(it) }
        }
    }

}