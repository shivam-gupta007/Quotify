package com.app.dailymotivation.feature_quote.data.data_source

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceOfPexelsApi {
    val api: PexelsImgApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PexelsImgApi::class.java)
    }
}