package com.example.kotlintoturial.Model

import com.google.gson.annotations.SerializedName

class ModelsResponse {
    @SerializedName("articles")
    var newsList: List<Models>? = null

    class sourceResponse {
        @SerializedName("sources")
        var sourcelist: List<Models.Source>? = null
    }

}