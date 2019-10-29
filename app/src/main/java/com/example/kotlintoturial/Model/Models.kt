package com.example.kotlintoturial.Model

import com.google.gson.annotations.SerializedName

class Models {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("author")
    val author: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("urlToImage")
    var urlToImage: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("publishedAt")
    val publisher: String? = null

    class Source {
        @SerializedName("id")
        var id: String? = null

        @SerializedName("name")
        var name: String? = null
    }
}