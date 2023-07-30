package com.app.quotify.di

import android.app.Application
import androidx.room.Room
import com.app.quotify.feature_quote.data.data_source.local.QuoteDatabase
import com.app.quotify.feature_quote.data.data_source.remote.service.PexelsImgApi
import com.app.quotify.feature_quote.data.data_source.remote.service.QuotableApi
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
        quotableApi: QuotableApi
    ): QuoteRepositoryImpl {
        return QuoteRepositoryImpl(
            quoteDatabase = db,
            quotableApi = quotableApi,
            pexelsImgApi = pexelsImgApi
        )
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