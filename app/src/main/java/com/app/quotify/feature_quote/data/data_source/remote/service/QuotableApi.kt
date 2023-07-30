package com.app.quotify.feature_quote.data.data_source.remote.service

import com.app.quotify.feature_quote.data.data_source.remote.adapter.NetworkResult
import com.app.quotify.feature_quote.domain.model.QuotableQuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotableApi {
    @GET("quotes?maxLength=140&limit=20")
    suspend fun getQuotes(
        @Query("page") page: Int
    ): Response<QuotableQuoteList>
}
