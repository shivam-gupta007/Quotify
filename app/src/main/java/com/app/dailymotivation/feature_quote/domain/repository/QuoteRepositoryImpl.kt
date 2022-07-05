package com.app.dailymotivation.feature_quote.domain.repository

import com.app.dailymotivation.feature_quote.data.data_source.RetrofitInstanceOfPexelsApi
import com.app.dailymotivation.feature_quote.data.data_source.RetrofitInstanceOfQuoteApi
import com.app.dailymotivation.feature_quote.domain.modal.api_response_class.PexelsApiResponseList
import com.app.dailymotivation.feature_quote.domain.modal.api_response_class.QuoteApiResponseList
import retrofit2.Response
import java.util.*

class QuoteRepositoryImpl {

    private val imgTypeList =
        listOf("nature", "water", "galaxy", "sky", "mountains", "earth", "forest")

    suspend fun getImages(): Response<PexelsApiResponseList> {
        val randomValue = Random().nextInt(6)

        return RetrofitInstanceOfPexelsApi.api.getImages(
            "${imgTypeList[if (randomValue != 0) randomValue else 6]},$imgTypeList[0]"
        )
    }

    suspend fun getQuote(): Response<QuoteApiResponseList> {
        return RetrofitInstanceOfQuoteApi.api.getQuotes(page = Random().nextInt(95))
    }
}
