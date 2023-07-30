package com.app.quotify.di

import com.app.quotify.BuildConfig
import com.app.quotify.feature_quote.data.data_source.remote.adapter.NetworkResultCallAdapterFactory
import com.app.quotify.feature_quote.data.data_source.remote.service.PexelsImgApi
import com.app.quotify.feature_quote.data.data_source.remote.service.QuotableApi
import com.app.quotify.feature_quote.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        val builder = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor());
        }

        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
        }

        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .client(builder.build())
    }

    @Provides
    @Singleton
    fun providePexelsApiService(retrofitBuilder: Retrofit.Builder): PexelsImgApi {
        return retrofitBuilder
            .baseUrl(Constants.PEXELS_API_ENDPOINT)
            .build()
            .create(PexelsImgApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuotableApiService(retrofitBuilder: Retrofit.Builder): QuotableApi {
        return retrofitBuilder
            .baseUrl(Constants.QUOTABLE_API_ENDPOINT)
            .build()
            .create(QuotableApi::class.java)
    }

}