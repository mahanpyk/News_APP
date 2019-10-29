package com.example.kotlintoturial.WebService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_Client {
    var retrofit: Retrofit? = null
    val Base_URL = "https://newsapi.org/v2/"

       fun getclient(): Retrofit? {
            if (retrofit == null) {
                retrofit =
                    Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit
        }
}