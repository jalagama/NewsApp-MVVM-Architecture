package com.jalagama.newsapp_mvvm_architecture.utils

object Languages {

    var languageList = listOf<Pair<String, String>>(

        Pair("ar", "Arabic"),
        Pair("de", "German"),
        Pair("en", "English"),
        Pair("es", "Spanish"),
        Pair("fr", "French"),
        Pair("he", "Hebrew"),
        Pair("it", "Italian"),
        Pair("nl", "Dutch"),
        Pair("no", "Norwegian"),
        Pair("pt", "Portuguese"),
        Pair("ru", "Russian"),
        Pair("se", "Northern Sami"),
        Pair("ud", "Urdu"),
        Pair("zh", "Chinese"),
        Pair("ka", "Kannada")

    ).sortedBy { it.second }
}