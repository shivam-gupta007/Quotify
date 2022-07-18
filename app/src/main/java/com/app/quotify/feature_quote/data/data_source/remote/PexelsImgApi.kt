package com.app.quotify.feature_quote.data.data_source.remote

import com.app.quotify.BuildConfig
import com.app.quotify.feature_quote.domain.modal.PexelsImageList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsImgApi {
    @Headers("Authorization: ${BuildConfig.PEXELS_API_KEY}")
    @GET("v1/search?per_page=20")
    suspend fun getImages(
        @Query("query") query: String
    ): Response<PexelsImageList>
}

