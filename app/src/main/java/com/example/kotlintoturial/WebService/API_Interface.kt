package com.example.kotlintoturial.WebService

import com.example.kotlintoturial.Model.ModelsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API_Interface {
    @GET("top-headlines?country=us&apiKey=bc8dd652e95c494f8497851ec40eb857")
    fun getnews() :Call<ModelsResponse>

    @GET("sources?apiKey=bc8dd652e95c494f8497851ec40eb857")
    fun getsources():Call<ModelsResponse.sourceResponse>

    @GET("top-headlines")
    fun getnewssource(@Query("sources") sources:String, @Query("apiKey") apikey:String):Call<ModelsResponse>
}