package com.jalagama.newsapp_mvvm_architecture.data.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source")
    val source: Source = Source(),
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val urlToImage: String = "",
) {
}