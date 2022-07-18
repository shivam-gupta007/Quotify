package com.app.quotify.feature_quote.data.data_source.remote

import com.app.quotify.feature_quote.domain.modal.QuotableQuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {
    @GET("quotes?maxLength=140&limit=20")
    suspend fun getQuotes(
        @Query("page") page: Int
    ): Response<QuotableQuoteList>
}
