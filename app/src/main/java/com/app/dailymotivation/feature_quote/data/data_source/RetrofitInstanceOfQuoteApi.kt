package com.app.dailymotivation.feature_quote.data.data_source

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceOfQuoteApi {

    val api: QuoteApi by lazy{
        Retrofit.Builder()
            .baseUrl("https://quotable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

}