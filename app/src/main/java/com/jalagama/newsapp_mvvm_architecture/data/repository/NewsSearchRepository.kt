package com.jalagama.newsapp_mvvm_architecture.data.repository

import com.jalagama.newsapp_mvvm_architecture.data.api.NetworkService
import com.jalagama.newsapp_mvvm_architecture.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSearchRepository @Inject constructor(private val networkService: NetworkService) {

    fun getSearchedNews(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsBySearch(query))
        }.map { it.articles }
    }
}