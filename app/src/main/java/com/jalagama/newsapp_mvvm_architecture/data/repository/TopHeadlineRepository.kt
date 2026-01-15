package com.jalagama.newsapp_mvvm_architecture.data.repository

import com.jalagama.newsapp_mvvm_architecture.data.api.NetworkService
import com.jalagama.newsapp_mvvm_architecture.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlinesByCountry(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlinesByCountry(country))

        }.map { it.articles }

    }

    fun getTopHeadlinesBySource(source: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlinesBySource(source))

        }.map { it.articles }

    }

    fun getTopHeadlinesByLanguage(language: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlinesByLanguage(language))

        }.map { it.articles }

    }
}