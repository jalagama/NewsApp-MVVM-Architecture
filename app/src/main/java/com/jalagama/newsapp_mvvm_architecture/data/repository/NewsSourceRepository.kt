package com.jalagama.newsapp_mvvm_architecture.data.repository

import com.jalagama.newsapp_mvvm_architecture.data.api.NetworkService
import com.jalagama.newsapp_mvvm_architecture.data.model.Sources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourceRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSources(): Flow<List<Sources>> {
        return flow {
            emit(networkService.getNewsSources())
        }.map { it.sources }
    }
}