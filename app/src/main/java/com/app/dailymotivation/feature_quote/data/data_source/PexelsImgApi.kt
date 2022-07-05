package com.app.dailymotivation.feature_quote.data.data_source

import com.app.dailymotivation.feature_quote.domain.modal.api_response_class.PexelsApiResponseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsImgApi {
    @Headers("Authorization: 563492ad6f917000010000010a526196f91f43f8bb3408f3875b374a")
    @GET("v1/search")
    suspend fun getImages(@Query("query") query: String): Response<PexelsApiResponseList>
}