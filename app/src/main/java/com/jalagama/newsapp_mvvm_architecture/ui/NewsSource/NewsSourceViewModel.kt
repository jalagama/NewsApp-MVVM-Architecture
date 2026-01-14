package com.jalagama.newsapp_mvvm_architecture.ui.NewsSource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalagama.newsapp_mvvm_architecture.data.model.Sources
import com.jalagama.newsapp_mvvm_architecture.data.repository.NewsSourceRepository
import com.jalagama.newsapp_mvvm_architecture.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceViewModel @Inject constructor(private val newsSourceRepository: NewsSourceRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<UiState<List<Sources>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Sources>>> = _uiState

    init {
        fetchNewsSources()
    }

    private fun fetchNewsSources() {

        viewModelScope.launch {
            newsSourceRepository.getNewsSources()
                .catch { e -> _uiState.value = UiState.Error(e.message ?: "Unknown Error")}
                .collect { _uiState.value = UiState.Success(it) }

        }
        newsSourceRepository.getNewsSources()
    }

}