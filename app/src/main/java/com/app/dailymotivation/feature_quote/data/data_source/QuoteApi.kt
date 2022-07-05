package com.app.dailymotivation.feature_quote.data.data_source

import com.app.dailymotivation.feature_quote.domain.modal.api_response_class.QuoteApiResponseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("quotes?maxLength=140")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteApiResponseList>
}
