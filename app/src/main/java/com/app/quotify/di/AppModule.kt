package com.app.quotify.di

import android.app.Application
import androidx.room.Room
import com.app.quotify.feature_quote.data.data_source.local.QuoteDatabase
import com.app.quotify.feature_quote.data.data_source.remote.PexelsImgApi
import com.app.quotify.feature_quote.data.data_source.remote.QuoteApi
import com.app.quotify.feature_quote.data.repository.QuoteRepositoryImpl
import com.app.quotify.feature_quote.domain.use_cases.*
import com.app.quotify.feature_quote.util.Constants
import com.app.quotify.feature_quote.util.Constants.QUOTE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteDatabase(app: Application): QuoteDatabase {
        return Room.databaseBuilder(
            app,
            QuoteDatabase::class.java,
            QUOTE_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(
        db: QuoteDatabase,
        pexelsImgApi: PexelsImgApi,
        quoteApi: QuoteApi
    ): QuoteRepositoryImpl {
        return QuoteRepositoryImpl(
            quoteDatabase = db,
            quoteApi = quoteApi,
            pexelsImgApi = pexelsImgApi
        )
    }

    @Provides
    @Singleton
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder()
            .baseUrl(Constants.QUOTE_API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun providePexelsImgApi(): PexelsImgApi {
        return Retrofit.Builder()
            .baseUrl(Constants.PEXELS_API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PexelsImgApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteUseCases(quoteRepositoryImpl: QuoteRepositoryImpl): QuoteUseCases{
        return QuoteUseCases(
            AddQuoteToFav(quoteRepositoryImpl),
            RemoveQuoteFromFav(quoteRepositoryImpl),
            GetQuotesFromApi(quoteRepositoryImpl),
            GetQuotesFromDb(quoteRepositoryImpl)
        )
    }


}