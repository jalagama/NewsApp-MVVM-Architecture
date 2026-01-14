package com.jalagama.newsapp_mvvm_architecture.data.model

import com.google.gson.annotations.SerializedName

data class Sources(

    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",

)